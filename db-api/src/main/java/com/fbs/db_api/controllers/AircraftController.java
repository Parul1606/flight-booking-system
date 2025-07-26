package com.fbs.db_api.controllers;

import com.fbs.db_api.models.Aircraft;
import com.fbs.db_api.repositories.AircraftRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/db/aircraft")
public class AircraftController {

    AircraftRepo aircraftRepo;

    @Autowired
    public AircraftController(AircraftRepo aircraftRepo){
        this.aircraftRepo = aircraftRepo;
    }

    @PostMapping("/save")
    public Aircraft saveAircraft(@RequestBody Aircraft aircraft){
        aircraftRepo.save(aircraft);
        return aircraft;
    }
}
