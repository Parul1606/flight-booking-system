package com.fbs.central_api.service;

import com.fbs.central_api.connectors.DBApiConnector;
import com.fbs.central_api.dto.AirlineRegistrationDto;
import com.fbs.central_api.models.Airline;
import com.fbs.central_api.models.AppUser;
import com.fbs.central_api.utility.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AirlineService {

    Mapper mapper;
    DBApiConnector dbApiConnector;
    UserService userService;
    MailService mailService;

    @Autowired
    public AirlineService(Mapper mapper,
                          DBApiConnector dbApiConnector,
                          UserService userService,
                          MailService mailService){
        this.mapper = mapper;
        this.dbApiConnector = dbApiConnector;
        this.userService = userService;
        this.mailService = mailService;
    }

    /*
    This function work is to call db api and save airline  details in airline table and airline admin details in users table
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
        airline = dbApiConnector.callCreatedAirlineEndpoint(airline);
        //when we have created both the inactive records for airline as well as airline admin
        // We need to mail app admin that this airline is trying to register into your application.
        // We need to think something how we can mail?
        // We will be creating another microservice whose work is to send notification
        // now we need to mail application admin regarding airline-registration-request
        // so, to mail we require application admin object
        // we need to mail all the system admins so, we need to get all the system admins from the table.
        List<AppUser> systemAdminList = userService.getAllSystemAdmins();
        //mail all system admins
        mailService.mailSystemAdminForAirlineRegistration(systemAdminList, airline);

        return airline;
    }
}
