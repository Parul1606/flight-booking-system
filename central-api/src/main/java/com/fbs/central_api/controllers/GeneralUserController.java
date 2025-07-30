package com.fbs.central_api.controllers;

import com.fbs.central_api.dto.LoginDto;
import com.fbs.central_api.exceptions.InvalidCredentials;
import com.fbs.central_api.service.FlightService;
import com.fbs.central_api.service.UserService;
import com.fbs.central_api.utility.AuthUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Purpose of this general user controller to have all the common endpoints of all the type of users.
 */
@RestController
@RequestMapping("/api/v1/central/user")
public class GeneralUserController {

    UserService userService;
    AuthUtility authUtility;
    FlightService flightService;

    @Autowired
    public GeneralUserController(UserService userService,
                                 AuthUtility authUtility,
                                 FlightService flightService) {
        this.userService = userService;
        this.authUtility = authUtility;
        this.flightService = flightService;
    }


    @GetMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto){
        try {
            String token = userService.isValidCredentials(loginDto.getEmail(), loginDto.getPassword());
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (InvalidCredentials e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/search")
    public Object searchFlight(@RequestParam String sourceAirport,
                                       @RequestParam String destinationAirport,
                                       @RequestParam String dateTime){
        // flight service
        return flightService.searchFlight(sourceAirport,destinationAirport, dateTime);
    }

}
