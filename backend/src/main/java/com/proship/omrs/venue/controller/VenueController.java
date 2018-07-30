package com.proship.omrs.venue.controller;

import com.proship.omrs.venue.entity.Venue;
import com.proship.omrs.venue.entity.VenueMainShard;
import com.proship.omrs.venue.entity.VenueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/venue/")
public class VenueController {

    @GetMapping("all")
    Collection getAll(){


        return VenueMap.getVenueMap().values();
    }

    @GetMapping("{id}")
    Venue getOne(@PathVariable("id")Long id){
        return VenueMap.getVenue(id);
    }
}
