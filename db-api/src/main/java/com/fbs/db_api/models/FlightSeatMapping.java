package com.fbs.db_api.models;

/*
  this flight seat mapping model will only be used for non-connecting flights
 */

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "flightseatmapping")
public class FlightSeatMapping {

    // id	flightId	classname	range	baseprice	windowprice

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @ManyToOne
    Flight flight;

    String className;

    String range;  // 1-20

    int basePrice;

    int windowPrice;

    int totalWindow;
}
