package com.proship.omrs.candidate.repository;

import com.proship.omrs.candidate.candidate.entity.ParticipantNameTts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ParticipantNameTtsRepository extends JpaRepository<ParticipantNameTts,Long>{

    @Query("select n.participant.id from ParticipantNameTts n where lower(n.firstName) like :firstName or " +
            "n.otherName like :firstName")
    Set<Long> findParticipantIdByFirstName(@Param("firstName") String firstName);

    @Query("select n.participant.id from ParticipantNameTts n where lower( n.lastName) like :lastName")
    Set<Long> findParticipantIdByLastName(@Param("lastName") String lastName);

    @Query("select n.participant.id from ParticipantNameTts n where lower(concat( n.firstName,n.otherName , n.lastName))" +
            " like :name ")
    Set<Long>findParticipantIdByAnyName(@Param("name") String name);
}
