package com.proship.omrs.gig.repository;

import com.proship.omrs.gig.entity.GigPeriodShard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GigPeriodRepository extends JpaRepository<GigPeriodShard,Long>{
}
