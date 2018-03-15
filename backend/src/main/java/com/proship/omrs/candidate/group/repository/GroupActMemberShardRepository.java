package com.proship.omrs.candidate.group.repository;

import com.proship.omrs.candidate.participant.entity.ParticipantAct;
import com.proship.omrs.candidate.group.entity.GroupActMemberShard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupActMemberShardRepository extends JpaRepository<GroupActMemberShard,Long> {

    @Query("select g.act.id  from GroupActMemberShard g where g.nexttransactiontime > current_date " +
            "group by g.act.id having  count (g.act.id)  >= :min and  count (g.act.id)  <= :max")
    List<Long> findBandBySize(@Param(value = "min") long min, @Param(value = "max") long max);


    @Query("select distinct g.participantActs from GroupActMemberShard g where g.act.id = :id and g.nexttransactiontime > current_date ")
    List<ParticipantAct>findBandMembers(@Param(value = "id") Long id);
}
