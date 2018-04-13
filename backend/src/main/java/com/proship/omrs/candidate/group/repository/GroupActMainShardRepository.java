package com.proship.omrs.candidate.group.repository;

import com.proship.omrs.candidate.group.entity.GroupActMainShard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupActMainShardRepository extends JpaRepository<GroupActMainShard,Long>{

    @Query("select act.act.id from GroupActMainShard act where act.name like :name and act.nexttransactiontime > now() " +
            "and act.validendtime > now() ")
    List<Long> findBandByName(@Param(value = "name") String name);

}
