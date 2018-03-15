package com.proship.omrs.system.airport.repository;

import com.proship.omrs.system.airport.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport,Long> {
}
