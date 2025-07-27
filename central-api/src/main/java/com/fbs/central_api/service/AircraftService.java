package com.fbs.central_api.service;

import com.fbs.central_api.connectors.DBApiConnector;
import com.fbs.central_api.dto.AircraftRegistrationDto;
import com.fbs.central_api.enums.UserType;
import com.fbs.central_api.exceptions.UnAuthorizedException;
import com.fbs.central_api.models.Aircraft;
import com.fbs.central_api.models.Airline;
import com.fbs.central_api.models.AppUser;
import com.fbs.central_api.utility.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AircraftService {

    UserService userService;
    AirlineService airlineService;
    Mapper mapper;
    DBApiConnector dbApiConnector;

    @Autowired
    public AircraftService(UserService userService,
                           AirlineService airlineService,
                           Mapper mapper,
                           DBApiConnector dbApiConnector){
        this.userService = userService;
        this.airlineService = airlineService;
        this.mapper = mapper;
        this.dbApiConnector = dbApiConnector;
    }

    public Aircraft getAircraftById(UUID id){
        return dbApiConnector.callGetAircraftById(id);
    }

    public Aircraft registerAircraft(AircraftRegistrationDto aircraftRegistrationDto,
                                 String authorization){
        // 1. Decrypt authorization and bring airline details
        String token = authorization.substring(7);
        AppUser airlineAdmin = userService.getUserFromToken(token);
        if(!airlineAdmin.getUserType().equals(UserType.AIRLINE_ADMIN.toString())){
            throw new UnAuthorizedException("User is not allowed to register aircraft");
        }
        // with the help of airlineAdmin we need to get the airline object
        Airline airline = airlineService.getAirlineByAdminId(airlineAdmin.getId());
        // Mapping logic to map aircraftDto to aircraft
        Aircraft aircraft = mapper.mapAircraftDtoToAircraft(aircraftRegistrationDto, airline);
        // now we need to call save aircraft method.
        return saveAircraft(aircraft);
    }

    public Aircraft saveAircraft(Aircraft aircraft){
        // we need to call dbApiConnector
        return dbApiConnector.callSaveAircraftEndpoint(aircraft);
    }
}
