package com.fbs.db_api.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/*
this class is going to represent booking details
Direct Flight -> Delhi to Mumbai (subflight list will be empty)
Connecting flight -> subFlight list will have all tbe subflight passenger is going to cover
//delhi to mumbai to chandigarh to sikkim
// subFlight -> [(Delhi to Mumbai), (Mumbai to Chandigarh)]
 */
@Data
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    @ManyToOne
    Flight flight;
    @ManyToMany
    List<SubFlight> subFlights;
    @ManyToOne
    AppUser bookedBy;
    int totalAmount;
    String passengerName;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}