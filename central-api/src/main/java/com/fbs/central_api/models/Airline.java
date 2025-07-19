package com.fbs.central_api.models;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Airline {
    UUID id;
    String website;
    String airlineName;
    String companyName;
    int employees;
    int totalFlights;
    AppUser admin;
    String status;
}
