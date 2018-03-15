package com.proship.omrs.candidate.residency.repository;

import com.proship.omrs.base.repository.CustomizedRepository;
import com.proship.omrs.candidate.residency.entity.ParticipantResidencyOverride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public interface ParticipantResidencyOverrideRepository extends
        CustomizedRepository<ParticipantResidencyOverride,Long> {

    @Query("select r.participant.id from ParticipantResidencyOverride r where r.nexttransactiontime> current_date and" +
            " r.participantResidency.countryId = :countryId")
    Set<Long> findCandidateIdByResidency(@Param("countryId") Long countryId);

    List<ParticipantResidencyOverride> findByIdIn(List<Long> ids);

    @Query("select r from ParticipantResidencyOverride r where r.nexttransactiontime > :date and r.participant.id = :candidate")
    List<ParticipantResidencyOverride> findByParticipantAndAndNexttransactiontimeIsAfter(@Param("candidate")Long id,
                                                                                         @Param("date") Timestamp date);
}
