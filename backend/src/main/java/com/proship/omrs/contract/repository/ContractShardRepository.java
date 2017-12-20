package com.proship.omrs.contract.repository;

import com.proship.omrs.contract.entity.ContractMainShard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.Set;

public interface ContractShardRepository extends JpaRepository<ContractMainShard,Long> {

    @Query(value = "select act.id from contract_main_shard c join contract_status_cancel_tts csct " +
            "on c.contract_id = csct.contract_id INNER JOIN participant_act act on c.act_id = act.id " +
            "JOIN participant_name_tts pnt on act.participant_id = pnt.participant_id where c.gig_id in ( :gigIds)  " +
             "and csct.nexttransactiontime > current_date and csct.cancel = false and act.active = true " +
            "and act_id not in (1,2,2576,2577) and act.active = true and c.nexttransactiontime > current_date " +
            "and pnt.nexttransactiontime>current_date",
                    nativeQuery = true)

    Set<BigInteger> findParticipantByGig(@Param("gigIds") Set<Long> gigIds);

    @Query(value = "select act.id from contract_main_shard c join contract_status_cancel_tts csct " +
            "on c.contract_id = csct.contract_id INNER JOIN participant_act act on c.act_id = act.id " +
            "JOIN participant_name_tts pnt on act.participant_id = pnt.participant_id where c.gig_id in ( :gigIds)  " +
            "and c.position = :position and csct.nexttransactiontime > current_date and " +
            "csct.cancel = false and act.active = true and act_id not in (1,2,2576,2577) and " +
            "act.active = true and c.nexttransactiontime > current_date and pnt.nexttransactiontime>current_date",
            nativeQuery = true)
    Set<BigInteger> findParticipantByGigAndRank(@Param("gigIds") Set<Long> gigIds, @Param("position") Long position);


    @Query(value = "select act.id from contract_main_shard c join contract_status_cancel_tts csct " +
            "on c.contract_id = csct.contract_id INNER JOIN participant_act act on c.act_id = act.id " +
            " JOIN participant_name_tts pnt on act.participant_id = pnt.participant_id " +
            "where c.gig_id in ( :gigIds) and c.position in :positions and csct.nexttransactiontime > " +
            "current_date and  csct.cancel = false and act.active = true and act_id not in (1,2,2576,2577) " +
            "and act.active = true and c.nexttransactiontime > current_date and pnt.nexttransactiontime>current_date",
            nativeQuery = true)
    Set<BigInteger> findParticipantByGigAndRankGroup(@Param("gigIds") Set<Long> gigIds,
                                                     @Param("positions") Set<Long> positions);


    @Query(value = "select act.id from contract_main_shard c join contract_status_cancel_tts csct " +
            "on c.contract_id = csct.contract_id INNER JOIN contract con on c.contract_id = con.id " +
            " INNER JOIN participant_act act on c.act_id = act.id INNER JOIN participant_name_tts pnt" +
            " on act.participant_id = pnt.participant_id where c.gig_id in ( :gigIds)  " +
            "and c.position in :positions and csct.nexttransactiontime > current_date and " +
            "csct.cancel = false and act.active = true AND con.non_ps = FALSE " +
            "and act_id not in (1,2,2576,2577) and act.active = true and c.nexttransactiontime > current_date and " +
            "pnt.nexttransactiontime>current_date",
            nativeQuery = true)
    Set<BigInteger> findParticipantByGigAndRankGroupExcludingNonProship
            (@Param("gigIds") Set<Long> gigIds, @Param("positions") Set<Long> positions);


    @Query(value = "select act.id from contract_main_shard c join contract_status_cancel_tts csct " +
            "on c.contract_id = csct.contract_id INNER JOIN contract con on c.contract_id = con.id " +
            " INNER JOIN participant_act act on c.act_id = act.id INNER JOIN participant_name_tts pnt " +
            "on act.participant_id = pnt.participant_id where c.gig_id in ( :gigIds)  " +
            "and c.position = :position and csct.nexttransactiontime > current_date and " +
            "csct.cancel = false and act.active = true AND con.non_ps = FALSE " +
            "and act_id not in (1,2,2576,2577) and act.active = true and c.nexttransactiontime > current_date " +
            "and pnt.nexttransactiontime>current_date",
            nativeQuery = true)
    Set<BigInteger> findParticipantByGigAndRankExcludingNonProship
            (@Param("gigIds") Set<Long> gigIds, @Param("position") Long position);



    @Query(value = "select act.id from contract_main_shard c join contract_status_cancel_tts csct " +
            "on c.contract_id = csct.contract_id INNER JOIN contract con on c.contract_id = con.id " +
            " INNER JOIN participant_act act on c.act_id = act.id join participant_name_tts pnt on act.participant_id" +
            " = pnt.participant_id where c.gig_id in ( :gigIds) and csct.nexttransactiontime > current_date and " +
            "csct.cancel = false and act.active = true AND con.non_ps = FALSE " +
            "and act_id not in (1,2,2576,2577) and act.active = true and c.nexttransactiontime > current_date " +
            "and pnt.nexttransactiontime>current_date",
            nativeQuery = true)
    Set<BigInteger> findParticipantByGigExcludingNonProship
            (@Param("gigIds") Set<Long> gigIds);



}
