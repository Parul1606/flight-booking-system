package com.fbs.db_api.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.tool.schema.spi.SchemaTruncator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "airlines")
public class Airline {
    //id    airline name      airlinewebsite        airline company     official name     employees     totalflights

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @Column(unique = true, nullable = false)
    String website;

    @Column(unique = true, nullable = false)
    String airlineName;

    @Column(unique = true, nullable = false)
    String companyName;
    int employees;
    int totalFlights;
    @OneToOne
    AppUser admin;
    String status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
