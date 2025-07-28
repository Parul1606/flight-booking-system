package com.fbs.db_api.controllers;

import com.fbs.db_api.models.FlightSeatMapping;
import com.fbs.db_api.repositories.FlightSeatMappingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/db/seatmapping")
public class FlightSeatMappingController {

    FlightSeatMappingRepo flightSeatMappingRepo;

    @Autowired
    public FlightSeatMappingController(FlightSeatMappingRepo flightSeatMappingRepo){
        this.flightSeatMappingRepo = flightSeatMappingRepo;
    }


    @PostMapping("/create")
    public FlightSeatMapping createFlightSeatMapping(@RequestBody FlightSeatMapping flightSeatMapping){
        return flightSeatMappingRepo.save(flightSeatMapping);
    }
}
