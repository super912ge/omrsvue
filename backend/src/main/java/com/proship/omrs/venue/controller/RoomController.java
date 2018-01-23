package com.proship.omrs.venue.controller;

import com.proship.omrs.base.controller.BaseController;
import com.proship.omrs.venue.entity.Room;
import com.proship.omrs.venue.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/room/")
public class RoomController extends BaseController<Room,Long> {

    @Autowired
    RoomRepository roomRepository;

    public RoomController(CrudRepository<Room, Long> repo) {
        super(repo);
    }

    @RequestMapping("all")
    ResponseEntity<List<String>> getRooms(){
        List<String> result = roomRepository.findAllRooms();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
