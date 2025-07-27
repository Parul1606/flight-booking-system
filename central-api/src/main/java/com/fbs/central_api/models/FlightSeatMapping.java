package com.fbs.central_api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightSeatMapping extends SeatMapping{

    UUID id;
    Flight flight;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
