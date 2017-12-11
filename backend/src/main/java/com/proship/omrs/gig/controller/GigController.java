package com.proship.omrs.gig.controller;

import com.proship.omrs.base.controller.BaseController;
import com.proship.omrs.gig.entity.Gig;
import com.proship.omrs.gig.repository.GigRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/gig/")
public class GigController extends BaseController<Gig,Long> {
    public GigController(GigRepository repo) {
        super(repo);
    }
}
