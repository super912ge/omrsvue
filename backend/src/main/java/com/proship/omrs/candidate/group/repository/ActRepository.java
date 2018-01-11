package com.proship.omrs.candidate.group.repository;

import com.proship.omrs.candidate.group.entity.Act;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ActRepository extends JpaRepository<Act,Long>{


    @Query("select act.id from Act act where act.groupActMainShard.name like :name")
    List<Long> findBandByName(@Param(value = "name") String name);

    @Query("select act.id from Act act where act.groupActMemberShards.size >= :min and act.groupActMemberShards <= :max")
    List<Long> findBandBySize(@Param(value = "min") int min, @Param(value = "max") int max);

    @Query("select act.id from Act act where act.jobMainShards")
    List<Long> findBandByGig(Set<Long> clientIds, Set<Long> venueIds, Long gigTypeId);
}
