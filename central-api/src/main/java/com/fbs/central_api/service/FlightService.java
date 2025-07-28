package com.fbs.central_api.service;

import com.fbs.central_api.connectors.DBApiConnector;
import com.fbs.central_api.dto.FlightDetailsDto;
import com.fbs.central_api.dto.SeatMappingDto;
import com.fbs.central_api.enums.UserType;
import com.fbs.central_api.exceptions.UnAuthorizedException;
import com.fbs.central_api.models.*;
import com.fbs.central_api.utility.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class FlightService {

    UserService userService;
    Mapper mapper;
    AirlineService airlineService;
    AircraftService aircraftService;
    DBApiConnector dbApiConnector;

    @Autowired
    public FlightService(UserService userService,
                         Mapper mapper,
                         AirlineService airlineService,
                         AircraftService aircraftService,
                         DBApiConnector dbApiConnector){
        this.userService = userService;
        this.mapper = mapper;
        this.airlineService = airlineService;
        this.aircraftService = aircraftService;
        this.dbApiConnector = dbApiConnector;
    }

    public Flight createFlight(FlightDetailsDto flightDetailsDto,
                             String authorization){
        String token = authorization.substring(7);
        AppUser user = userService.getUserFromToken(token);
        if(!user.getUserType().equals(UserType.AIRLINE_ADMIN.toString())){
            throw new UnAuthorizedException("User is not allowed to create flight");
        }

        Airline airline = airlineService.getAirlineByAdminId(user.getId());
        Aircraft aircraft = aircraftService.getAircraftById(flightDetailsDto.getAircraftId());
        Flight flight = mapper.mapFlightDetailsDtoToFlightModel(flightDetailsDto, airline, aircraft);

        // we need to save the flight inside the database
        flight = dbApiConnector.callCreateFlightEndpoint(flight);
        // we need to create different classes for the flight
        List<SeatMappingDto> seatMappingDtos = flightDetailsDto.getSeatMappingDtos();
        for(int i=0; i<seatMappingDtos.size(); i++){
            SeatMappingDto seatMappingDto = seatMappingDtos.get(i);
            // mapper
            FlightSeatMapping flightSeatMapping = mapper.mapFLightSeatMappingDtoToModel(seatMappingDto, flight);
            // dbApiConnector to save the flightSeatMapping
            dbApiConnector.callCreateFlightSeatMapping(flightSeatMapping);
        }

        // now after mapping and creating flight we need to amil to the flight admin that your requested flight got created inside the system.
        return flight;

    }
}
