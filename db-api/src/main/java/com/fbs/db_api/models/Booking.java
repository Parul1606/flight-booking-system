package com.fbs.db_api.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;
import java.util.UUID;

/*
this class is going to represent booking details
Direct Flight -> Delhi to Mumbai (subflight list will be empty)
Connecting flight -> subFlight list will have all tbe subflight passenger is going to cover
//delhi to mumbai to chandigarh to sikkim
// subFlight -> [(Delhi to Mumbai), (Mumbai to Chandigarh)]
 */

public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    Flight flight;
    List<SubFlight> subFlights;
}