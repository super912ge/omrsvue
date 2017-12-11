package com.proship.omrs.venue.repository;

import com.proship.omrs.venue.entity.VenueMainShard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface VenueMainShardRepository extends JpaRepository<VenueMainShard,Long>{

    @Query("select v.venueId from VenueMainShard v where v.clientId in (:clientIds)")
    Set<Long> findByClientId(@Param("clientIds")Set<Long> clientIds);
}
