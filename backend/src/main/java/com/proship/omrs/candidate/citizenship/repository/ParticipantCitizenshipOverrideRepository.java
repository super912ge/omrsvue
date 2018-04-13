package com.proship.omrs.candidate.citizenship.repository;

import com.proship.omrs.base.repository.CustomizedRepository;
import com.proship.omrs.candidate.citizenship.entity.ParticipantCitizenshipOverride;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public interface ParticipantCitizenshipOverrideRepository extends
        CustomizedRepository<ParticipantCitizenshipOverride,Long> {

    @Query("select r.participant.id from ParticipantCitizenshipOverride " +
            "r where r.nexttransactiontime> now() and" +
            " r.participantCitizenship.countryId = :countryId")
    Set<Long> findCandidateIdByCitizenship(@Param("countryId") Long countryId);

    List<ParticipantCitizenshipOverride> findParticipantCitizenshipOverrideByIdIn(List<Long> ids);

    @Query("select r from ParticipantCitizenshipOverride r where r.nexttransactiontime > :date and r.participant.id = :candidate")
    List<ParticipantCitizenshipOverride> findByParticipantAndAndNexttransactiontimeIsAfter(@Param("candidate")Long id,
                                                                                         @Param("date") Timestamp date);
}
