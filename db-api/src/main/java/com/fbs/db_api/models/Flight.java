package com.fbs.db_api.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "flights")
public class Flight {
    // id	airlineId	aircraftId	source Airport	destination airport	flight type	totalTime	boarding time	departure time	arrival time	isConnecting
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @ManyToOne
    Airline airline;

    @ManyToOne
    Aircraft aircraft;

    String sourceAircraft;

    String destinationAircraft;

    String flightType;  // International, Domestic, Emergency

    int totalTime;  // TotalTime in minutes

    LocalDateTime boardingTime;  // when you passengers need to sit in the aircraft

    int boardingMinutes;

    LocalDateTime departureTime;   // when aircraft is going to takeoff;   // IST Timezone

    LocalDateTime arrivalTime;   // when aircraft is going to land;  // EST Timezone

    boolean isConnecting;  // is this flight a connecting flight ? or not
}
