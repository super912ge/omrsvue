package com.proship.omrs.candidate.participant.repository;

import com.proship.omrs.candidate.participant.entity.ParticipantAct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ParticipantActRepository extends JpaRepository<ParticipantAct,Long>{

//    @Query("select p.id from ParticipantAct p where p.participant.id in :ids")
//    Set<Long> findIdByParticipantId(@Param("candidateIds") Set<Long> ids);
}
