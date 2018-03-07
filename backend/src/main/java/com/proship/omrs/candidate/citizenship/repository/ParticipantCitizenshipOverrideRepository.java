package com.proship.omrs.candidate.citizenship.repository;

import com.proship.omrs.base.repository.CustomizedRepository;
import com.proship.omrs.candidate.citizenship.entity.ParticipantCitizenshipOverride;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ParticipantCitizenshipOverrideRepository extends
        CustomizedRepository<ParticipantCitizenshipOverride,Long> {

    @Query("select r.participant.id from ParticipantCitizenshipOverride " +
            "r where r.nexttransactiontime> current_date and" +
            " r.participantCitizenship.countryId = :countryId")
    Set<Long> findCandidateIdByCitizenship(@Param("countryId") Long countryId);

}
