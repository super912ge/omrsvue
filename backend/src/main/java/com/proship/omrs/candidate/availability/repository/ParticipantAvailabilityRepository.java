package com.proship.omrs.candidate.availability.repository;

import com.proship.omrs.candidate.availability.entity.ParticipantAvailabilityBts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Set;

public interface ParticipantAvailabilityRepository extends JpaRepository<ParticipantAvailabilityBts, Long>{

    @Query("select p.participant.id from ParticipantAvailabilityBts p where p.validstarttime <= :start and" +
            " p.validendtime >= :end and p.nexttransactiontime > now() ")
    Set<Long> findCandidateIdByAvailability(@Param("start") Date start, @Param("end") Date end);

    @Query("select p.participant.id from ParticipantAvailabilityBts p where p.validstarttime <= :start and" +
            " p.validendtime >= :end and p.nexttransactiontime > now() and p.level > :level")
    Set<Long> findCandidateIdByAvailabilityAndLevel(@Param("start") Date start, @Param("end") Date end,
                                                    @Param("level") Integer level);
}
