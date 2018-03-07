package com.proship.omrs.candidate.citizenship.controller;

import com.proship.omrs.candidate.citizenship.service.CitizenshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/citizenship/{id}")
public class CitizenshipController {

    @Autowired
    CitizenshipService citizenshipService;


    @RequestMapping("create/{id}")
    ResponseEntity<Map<String,Object>> addCitizenships(@PathVariable("id") Long id, @RequestBody List<Long> ids){

        List<Long> result = citizenshipService.addCitizenship(id,ids);

        Map<String,Object> m = new HashMap<>();

        m.put("success", true);

        m.put("result",result);

        return new ResponseEntity<Map<String, Object>>(m, HttpStatus.OK);

    }
}
