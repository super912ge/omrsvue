package com.proship.omrs.gig.controller;

import com.proship.omrs.base.controller.BaseController;
import com.proship.omrs.gig.entity.BandType;
import com.proship.omrs.gig.repository.BandTypeRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/gigType/")
public class GigTypeController extends BaseController<BandType,Long>{
    public GigTypeController(BandTypeRepository repo) {
        super(repo);
    }
}
