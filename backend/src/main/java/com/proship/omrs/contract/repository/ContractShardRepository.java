package com.proship.omrs.contract.repository;

import com.proship.omrs.contract.entity.ContractMainShard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ContractShardRepository extends JpaRepository<ContractMainShard,Long>
        {

    @Query("select c.act.id from ContractMainShard c where c.gig.id in ( :gigIds) " +
            "and act.id not in (1,2,2576,2577) and act.active = true and c.nexttransactiontime > current_date ")
    Set<Long> findParticipantByGig(@Param("gigIds") Set<Long> gigIds);

}
