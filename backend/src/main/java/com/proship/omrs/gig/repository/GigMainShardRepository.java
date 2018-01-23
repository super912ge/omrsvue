package com.proship.omrs.gig.repository;

import com.proship.omrs.gig.entity.GigMainShard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface GigMainShardRepository extends JpaRepository<GigMainShard,Long> {


    @Query("select g.gig.id from GigMainShard g where g.gigType = :gigType" +
            " and g.room.id in :rooms and g.nexttransactiontime > current_date ")
    Set<Long> findGigIdByRoomAndGigType(@Param("rooms")Set<Long> rooms, @Param("gigType") Long gigType);

    @Query("select g.gig.id from GigMainShard g where g.room.id " +
            " in :rooms and g.nexttransactiontime > current_date ")
    Set<Long> findGigIdByRoom(@Param("rooms")Set<Long> rooms);

    @Query("select g.gig.id from GigMainShard g where g.gigType = :gigType " +
            "and g.nexttransactiontime > current_date")
    Set<Long> findGigIdByGigType(@Param("gigType")Long gigType);


    @Query("select g.gig.id from GigMainShard g where g.label like :name and g.nexttransactiontime > current_date")
    Set<Long>findGigIdByName(@Param("name")String name);


    @Query("select g.gig.id from GigMainShard g where g.responsible.id = :id and g.nexttransactiontime > current_date" +
            " and g.validendtime > current_date ")
    Set<Long>findGigIdByAccountManager(@Param("id")Long id);
}
