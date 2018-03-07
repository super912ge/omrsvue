package com.proship.omrs.candidate.residency.controller;


import com.proship.omrs.candidate.residency.service.ResidencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/residency/")
public class ParticipantResidencyController {

    @Autowired
    ResidencyService residencyService;

    @RequestMapping("create/{id}")
    public ResponseEntity<Map<String,Object>> addResidency(@PathVariable("id")Long id, List<Long>ids){

        List<Long> result = residencyService.addResidency(id,ids);

        Map<String,Object> map = new HashMap<>();

        map.put("success",true);

        map.put("result", result);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
