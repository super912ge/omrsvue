package com.proship.omrs.gig.repository;

import com.proship.omrs.gig.entity.GigTerritoryShard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GigTerritoryRepository extends JpaRepository<GigTerritoryShard,Long>{
}
