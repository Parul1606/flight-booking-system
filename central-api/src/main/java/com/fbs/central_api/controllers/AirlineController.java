package com.fbs.central_api.controllers;

import com.fbs.central_api.dto.AirlineRegistrationDto;
import com.fbs.central_api.models.Airline;
import com.fbs.central_api.service.AirlineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/central/airline")
@Slf4j
public class AirlineController {

    AirlineService airlineService;

    @Autowired
    public AirlineController(AirlineService airlineService){
        this.airlineService = airlineService;
    }

    /*
    this method will get called when this particular /api/v1/central/airline/register will get triggered
    and for making this endpoint work we have created the whole notification api microservice airline/appadmin registration part to send notification the the users
     */

    @PostMapping("/register")
    public ResponseEntity registerAirline(@RequestBody AirlineRegistrationDto airlineDetails){
        // Airline details -> we need to catch those airline details json in an airlineDetailsDto
        // From here we should call airlineService for further processing
        // From this method we are going to call
        log.info("airlineRegistration method got called with the request body : " + airlineDetails.toString());
        log.info("calling airlineService registerAirline method");
        // from here we will call airline service register airline method
        Airline airline = airlineService.registerAirline(airlineDetails);
        return new ResponseEntity(airline, HttpStatus.CREATED);
    }

    /*
    This endpoint will get triggered from the mail which we have sent to System Admin.
    When System Admin will click over the accept button this endpoint will get triggered.
    Work of this endpoint to change the status of Airline to ACTIVE also it will changing the status of airlineAdmin also to active
     */
    @GetMapping("/request/accept/{airlineId}")
    public void acceptAirlineRequest(@PathVariable UUID airlineId){
        log.info("airlineId : " + airlineId.toString());
        // we will be calling our airlineService to change the status of airline and airline admin to active
        airlineService.acceptAirlineRequest(airlineId);
    }
}
