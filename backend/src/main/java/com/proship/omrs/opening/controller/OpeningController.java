package com.proship.omrs.opening.controller;


import com.proship.omrs.opening.param.Opening;
import com.proship.omrs.opening.param.OpeningSearchParam;
import com.proship.omrs.opening.service.OpeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/opening/")
public class OpeningController {

    @Autowired
    OpeningService openingService;

    @RequestMapping("search")
    public ResponseEntity<List<Opening>> getOpenings(@RequestBody OpeningSearchParam in){

        List<Opening> result = openingService.findOpeningsByCondition(in);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
