package com.proship.omrs.venue.controller;

import com.proship.omrs.base.controller.BaseController;
import com.proship.omrs.venue.entity.Venue;
import com.proship.omrs.venue.repository.VenueRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VenueController extends BaseController<Venue,Long>{
    public VenueController(VenueRepository repo) {
        super(repo);
    }
}
