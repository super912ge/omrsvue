package com.proship.omrs.gig.repository;

import com.proship.omrs.candidate.availability.entity.AvailabilityLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailabilityLevelRepository extends JpaRepository<AvailabilityLevel,Long> {
}
