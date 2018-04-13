package com.proship.omrs.candidate.name.repository;

import com.proship.omrs.candidate.name.entity.ParticipantActName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ParticipantActNameRepository extends JpaRepository<ParticipantActName,Long>{

    @Query("select p.participantAct.participant.id from ParticipantActName p where p.name like :name " +
            "and p.nexttransactiontime > now() ")
    Set<Long> findCandidateIdByName(@Param("name") String name);
}
