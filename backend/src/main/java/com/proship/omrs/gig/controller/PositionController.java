package com.proship.omrs.gig.controller;

import com.proship.omrs.base.controller.BaseController;
import com.proship.omrs.gig.entity.Position;
import com.proship.omrs.gig.repository.PositionRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rank/")
public class PositionController extends BaseController<Position,Long>{

    public PositionController(PositionRepository repo) {
        super(repo);
    }
}
