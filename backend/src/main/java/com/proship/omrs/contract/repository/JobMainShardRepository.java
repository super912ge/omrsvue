package com.proship.omrs.contract.repository;

import com.proship.omrs.contract.entity.JobMainShard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface JobMainShardRepository extends JpaRepository<JobMainShard,Long>{

    @Query("select distinct job.groupAct.id from JobMainShard job where job.gig.id in :gigIds and job.groupAct.id not in (4002) ")
    List<Long> findGroupIdByGigId(@Param("gigIds")Set<Long> gigIds);

}
