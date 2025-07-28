package com.fbs.db_api.repositories;

import com.fbs.db_api.models.FlightSeatMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FlightSeatMappingRepo extends JpaRepository<FlightSeatMapping, UUID> {
}
