package com.fbs.central_api.controllers;

import com.fbs.central_api.dto.AirlineRegistrationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/central/airline")
@Slf4j
public class AirlineController {


    @PostMapping("/register")
    public ResponseEntity registerAirline(@RequestBody AirlineRegistrationDto airlineDetails){
        // Airline details -> we need to catch those airline details json in an airlineDetailsDto
        // From here we should call airlineService for further processing
        // From this method we are going to call
        log.info("airlineRegistration method got called with the request body : " + airlineDetails.toString());
        log.info("calling airlineService registerAirline method");
    }
}
