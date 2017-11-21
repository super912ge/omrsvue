package com.proship.omrs.candidate.repository;

import com.proship.omrs.candidate.candidate.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ParticipantRepository extends JpaRepository<Participant,Long>{

    @Query(
            "select p.id from Participant p where p.evaluation.id in :ids"
    )
    Set<Long> findParticipantIdByEvaluationId(@Param("ids") Set<Long> ids);
}
