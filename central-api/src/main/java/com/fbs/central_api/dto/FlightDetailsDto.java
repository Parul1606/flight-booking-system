package com.fbs.central_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDetailsDto {
    String sourceAircraft;   // mumbai

    String destinationAircraft;  // new york

    String flightType;  // International, Domestic, Emergency

    int totalTime;  // TotalTime in minutes

    LocalDateTime boardingTime;  // when you passengers need to sit in the aircraft

    int boardingMinutes;

    LocalDateTime departureTime;   // when aircraft is going to takeoff;   // IST Timezone

    LocalDateTime arrivalTime;   // when aircraft is going to land;  // EST Timezone

    boolean isConnecting;  // is this flight a connecting flight ? or not

    UUID aircraftId;
    List<SubFlightDto> subFlightDtos;
    List<SeatMappingDto> seatMappingDtos;
}
