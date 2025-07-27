package com.fbs.db_api.controllers;

import com.fbs.db_api.models.Aircraft;
import com.fbs.db_api.repositories.AircraftRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @GetMapping("/{id}")
    public Aircraft getAircraftById(@PathVariable UUID id){
         return aircraftRepo.findById(id).orElse(null);
    }

}
