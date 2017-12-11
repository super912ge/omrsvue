package com.proship.omrs.candidate.repository;

import com.proship.omrs.candidate.candidate.entity.ParticipantResidencyOverride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ParticipantResidencyRepository extends JpaRepository<ParticipantResidencyOverride,Long>{

    @Query("select r.participant.id from ParticipantResidencyOverride r where r.nexttransactiontime> current_date and" +
            " r.participantResidency.countryId = :countryId")
    Set<Long> findCandidateIdByResidency(@Param("countryId") Long countryId);
}
