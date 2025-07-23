package com.fbs.db_api.controllers;

import com.fbs.db_api.models.Airline;
import com.fbs.db_api.repositories.AirlineRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/db/airline")
public class AirlineController {

    AirlineRepo airlineRepo;

    public AirlineController(AirlineRepo airlineRepo){
        this.airlineRepo = airlineRepo;
    }

    @PostMapping("/create")
    public ResponseEntity createAirline(@RequestBody Airline airline){
        // to save airline we require airline repository
        Airline airlineResp = airlineRepo.save(airline);
        return new ResponseEntity(airlineResp, HttpStatus.CREATED);
    }

    @GetMapping("/{airlineId}")
    public ResponseEntity getAirlineById(@PathVariable UUID airlineId){
        Airline airline =  airlineRepo.findById(airlineId).orElse(null);
        return new ResponseEntity<>(airline, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Airline airline){
        airlineRepo.save(airline);
        return new ResponseEntity(airline, HttpStatus.OK);
    }

}
