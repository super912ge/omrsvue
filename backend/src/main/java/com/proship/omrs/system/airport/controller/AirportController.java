package com.proship.omrs.system.airport.controller;

import com.proship.omrs.base.controller.BaseController;
import com.proship.omrs.system.airport.entity.Airport;
import com.proship.omrs.system.airport.repository.AirportRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/airport/")
public class AirportController extends BaseController<Airport,Long> {
    public AirportController(AirportRepository repo) {
        super(repo);
    }
}
