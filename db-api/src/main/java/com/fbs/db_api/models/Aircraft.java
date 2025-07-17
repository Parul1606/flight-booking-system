package com.fbs.db_api.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name= "aircrafts")
public class Aircraft {
    // id aircraftname  modelname    developercompany    planemodel    totalflights    builddate   airlineid   capacity
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    int modelNumber;

    String manufacture;

    String modelName;

    int totalFlights;

    LocalDate buildDate;

    @ManyToOne
    Airline airline;

    int capacity;

}
