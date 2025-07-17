package com.fbs.db_api.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

/*
this booking table we are strictly going to use for non-connecting flights
* */
@Entity
@Data
@Table(name = "flightseatbooked")
public class FlightSeatBooked {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @ManyToOne
    Flight flight;

    @ManyToOne
    AppUser bookedBy;

    String passengerName;

    boolean above18;

    int seatNumber;

}
