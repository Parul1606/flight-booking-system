package com.fbs.db_api.dto;

import com.fbs.db_api.models.Flight;
import lombok.Data;

import java.util.List;

@Data
public class AllFlightDto {
    List<Flight> flights;
}
