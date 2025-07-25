package com.fbs.central_api.models;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class Aircraft {
    UUID id;
    int modelNumber;
    String manufacturer;
    String modelName;
    int totalFlights;
    LocalDate buildDate;
    Airline airline;
    int capacity;
}
