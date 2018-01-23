package com.proship.omrs.venue.repository;

import com.proship.omrs.venue.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface RoomRepository extends JpaRepository<Room,Long>{


    @Query("select r.id from Room r where r.venue in :venueIds")
    Set<Long> findByVenue(@Param(value = "venueIds") Set<Long> venueIds);


    @Query("select distinct r.name from Room r")
    List<String>findAllRooms();

    @Query("select r.id from Room r where r.venue in :venueIds and r.name = :room")
    Set<Long> findByVenueAndRoomName(@Param(value = "venueIds") Set<Long> venueIds, @Param(value = "room") String room);


    @Query("select r.id from Room r where r.name = :name")
    Set<Long>findByRoomName(@Param(value = "name") String room);
}
