package com.proship.omrs.opening.repository;

import com.proship.omrs.base.repository.CustomizedRepository;
import com.proship.omrs.opening.entity.RequisitionBts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface RequisitionRepository extends CustomizedRepository<RequisitionBts,Long>{


    @Query("select r from RequisitionBts r where r.gig.id in :gigIds " +
            "and r.validendtime <= :endTime and r.validstarttime >= :startTime and " +
            "r.nexttransactiontime > now()" )
    List<RequisitionBts> findAllByCondition(@Param("gigIds") List<Long> gigIds, @Param("startTime") Date startTime,
                                            @Param("endTime") Date endTime);
}
