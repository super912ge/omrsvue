package com.proship.omrs.system.country.controller;

import com.proship.omrs.base.controller.BaseController;
import com.proship.omrs.system.country.entity.Country;
import com.proship.omrs.system.country.repository.CountryRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/country/")
@RestController
public class CountryController extends BaseController<Country,Long>{
    public CountryController(CountryRepository repo) {
        super(repo);
    }
}
