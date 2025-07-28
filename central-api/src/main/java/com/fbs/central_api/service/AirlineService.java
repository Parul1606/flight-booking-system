package com.fbs.central_api.service;

import com.fbs.central_api.connectors.DBApiConnector;
import com.fbs.central_api.connectors.GeminiConnector;
import com.fbs.central_api.dto.AirlineRegistrationDto;
import com.fbs.central_api.enums.AirlineStatus;
import com.fbs.central_api.enums.UserStatus;
import com.fbs.central_api.models.Airline;
import com.fbs.central_api.models.AppUser;
import com.fbs.central_api.models.GeminiApiResponse;
import com.fbs.central_api.utility.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class AirlineService {

    Mapper mapper;
    DBApiConnector dbApiConnector;
    UserService userService;
    MailService mailService;
    GeminiConnector geminiConnector;

    @Autowired
    public AirlineService(Mapper mapper,
                          DBApiConnector dbApiConnector,
                          UserService userService,
                          MailService mailService,
                          GeminiConnector geminiConnector){
        this.mapper = mapper;
        this.dbApiConnector = dbApiConnector;
        this.userService = userService;
        this.mailService = mailService;
        this.geminiConnector = geminiConnector;
    }

    public Airline getAirlineById(UUID airlineId){
        // so, to get the airline by id we need to call database api
        // so, to call database api we require db-api connector
        return dbApiConnector.callGetAirlineByIdEndpoint(airlineId);
    }

    public Airline updateAirlineDetails(Airline airline){
        // we should call database api to update the airline object in the airline table
        // do we have any endpoint register inside database api connector ? that will take the airline object and update the status of airline to active.
        return dbApiConnector.callUpdateAirlineEndpoint(airline);
    }

    /*
    This function work is to call db api and save airline  details in airline table and airline appadmin details in users table
    */
    public Airline registerAirline(AirlineRegistrationDto airlineRegistrationDto){
        log.info("airlineService registerAirline method called: " + airlineRegistrationDto.toString());
        // before calling db api lets map the details which we are getting in dto to respective models
        // ideally we should not write mapping logic here we should keep it in different class
        AppUser airlineAdmin = mapper.mapAirlineDetailsDtoToAppUser(airlineRegistrationDto);
        // After creating airlineAdmin object we should call db-api to save this object to the table.
        // That means we need to connect with db-api AppUser registration endpoint
        // So, to connect with the dbapi app user registration endpoint we should create connector class
        log.info("Calling dbApiConnector callCreateUserMethod with payload: " + airlineAdmin.toString());
        airlineAdmin = dbApiConnector.callCreateUserEndpoint(airlineAdmin);
        // next step is mapping airlineRegistrationDto to Airline Object -> we need to write another mapper.
        Airline airline = mapper.mapAirlineDetailsDtoToAirlineObject(airlineRegistrationDto, airlineAdmin);
        // now we got the airline object e need to save this airline into the airline tan;e
        // So, to this airline into airline table we need to call database api connecotr
        // Internally dbapiconnector will be calling your createairline endpoint
        airline = dbApiConnector.callCreateAirlineEndpoint(airline);
        //when we have created both the inactive records for airline as well as airline appadmin
        // We need to mail app appadmin that this airline is trying to register into your application.
        // We need to think something how we can mail?
        // We will be creating another microservice whose work is to send notification
        // now we need to mail application appadmin regarding airline-registration-request
        // so, to mail we require application appadmin object
        // we need to mail all the system admins so, we need to get all the system admins from the table.
        List<AppUser> systemAdminList = userService.getAllSystemAdmins();
        //mail all system admins
        mailService.mailSystemAdminForAirlineRegistration(systemAdminList, airline);

        return airline;
    }

    public Airline acceptAirlineRequest(UUID airlineId){
        // 1. to get the airline object on the basis of Id.
        // 2. Update the status of airline as well status of airline Admin.
        // 3. Save those changes into their respective tables in the database.
        // 4. We need to mail airline admin that congratulations your request got approved.
        log.info("airlineId " + airlineId);
        Airline airline = getAirlineById(airlineId); // 1.
        airline.setStatus(AirlineStatus.ACTIVE.toString());
        airline.setUpdatedAt(LocalDateTime.now());
        airline = updateAirlineDetails(airline);
        // So, now we want save the changes of airline to airline table and airlineAdmin to user table.
        // Airline Admin -> update the status of airline admin to ACTIVE
        AppUser airlineAdmin = airline.getAdmin();
        airlineAdmin.setStatus(UserStatus.ACTIVE.toString());
        airlineAdmin.setUpdatedAt(LocalDateTime.now());
        userService.updateUserDetails(airlineAdmin);
        // Mail airline admin that your request got accepted now you are part of our application.

        mailService.notifyAcceptRequestToAirlineAdmin(airline);

        return airline;
    }

    public void rejectAirlineRequest(UUID airlineId){
        // verify the id of airline is correct or not?
        // if it is correct -> then fine else we will throw the exception
        Airline airline = this.getAirlineById(airlineId);
        airline.setStatus(AirlineStatus.REJECTED.toString());
        this.updateAirlineDetails(airline);

        // we need to generate rejection reasons, so we will be calling gemini api
        // lets design a prompt
        String prompt = "Generate Failure reason for the airline details : " + airline.toString();
        GeminiApiResponse geminiApiResponse = geminiConnector.callGeminiGenAIEndpoint(prompt);
        String res = geminiApiResponse.getCandidates().get(0).getContent().getParts().get(0).getText();

        // we need to mail this result to airline that his request got cancelled because of these reasons:
        // I need to call notification api such that Airline Admin will receive mail that these are the reasons your airline got rejected
        // Mail api ->

        mailService.notifyRejectRequestToAirlineAdmin(airline.getAdmin().getEmail(), airline.getAdmin().getName(), res);
    }

    public Airline getAirlineByAdminId(UUID adminId){
        // dbAPiconenctor to get the airline
        return dbApiConnector.getAirlineByAdminIdEndpoint(adminId);
    }

}
