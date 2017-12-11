package com.proship.omrs.venue.controller;

import com.proship.omrs.base.controller.BaseController;
import com.proship.omrs.venue.entity.VenueMainShard;
import com.proship.omrs.venue.repository.VenueMainShardRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/venuemainshard")
public class VenueMainShardController extends BaseController<VenueMainShard,Long>{
    public VenueMainShardController(VenueMainShardRepository repo) {
        super(repo);
    }
}
