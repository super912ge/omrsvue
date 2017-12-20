package com.proship.omrs.candidate.repository;

import com.proship.omrs.candidate.candidate.entity.Participant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ParticipantRepository extends PagingAndSortingRepository<Participant,Long> {

    @Query(
            "select p.id from Participant p where p.evaluation.id in :ids and p.nameTts is not null"
    )
    Set<Long> findParticipantIdByEvaluationId(@Param("ids") Set<Long> ids);

    @Query("select p from Participant p where p.id in :ids and p.nameTts.nexttransactiontime >current_date ")
    Page<Participant> findParticipantByIdIn(@Param("ids")Set<Long> ids, Pageable pageable);

    @Query("select p.id from Participant p where p.participantAct.id = :id")
    Long findParticipantByActId(@Param("id") Long id);
}
