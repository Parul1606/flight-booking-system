package com.fbs.central_api.service;

import com.fbs.central_api.connectors.DBApiConnector;
import com.fbs.central_api.dto.AirlineRegistrationDto;
import com.fbs.central_api.models.AppUser;
import com.fbs.central_api.utility.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AirlineService {

    Mapper mapper;
    DBApiConnector dbApiConnector;

    @Autowired
    public AirlineService(Mapper mapper,
                          DBApiConnector dbApiConnector){
        this.mapper = mapper;
        this.dbApiConnector = dbApiConnector;
    }

    /*
    This function work is to call db api and save airline  details in airline table and airline admin details in users table
    * */
    public void registerAirline(AirlineRegistrationDto airlineRegistrationDto){
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
    }
}
