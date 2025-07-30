package com.fbs.central_api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    // id	airlineId	aircraftId	source Airport	destination airport	flight type	totalTime	boarding time	departure time	arrival time	isConnecting
    UUID id;
    Airline airline;
    Aircraft aircraft;
    String sourceAirport;   // mumbai
    String destinationAirport;  // new york
    String flightType;  // International, Domestic, Emergency
    int totalTime;  // TotalTime in minutes
    LocalDateTime boardingTime;  // when you passengers need to sit in the aircraft
    int boardingMinutes;
    LocalDateTime departureTime;   // when aircraft is going to takeoff;   // IST Timezone
    LocalDateTime arrivalTime;   // when aircraft is going to land;  // EST Timezone
    boolean isConnecting;  // is this flight a connecting flight ? or not
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
