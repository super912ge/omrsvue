package com.proship.omrs.candidate.homeAirport.controller;

import com.proship.omrs.candidate.homeAirport.param.CreateHomeAirportParam;
import com.proship.omrs.candidate.homeAirport.param.UpdateHomeAirportParam;
import com.proship.omrs.candidate.homeAirport.service.HomeAirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/homeAirport/")
public class HomeAirportController {

    @Autowired
    HomeAirportService homeAirportService;

    @RequestMapping("/create/{id}")
    ResponseEntity<Map<String,Object>> create(@PathVariable("id")Long id, @RequestBody CreateHomeAirportParam param){

       Long result = homeAirportService.create(id,param);

        Map<String,Object> m = new HashMap<>();

        m.put("success", true);

        m.put("result",result);

        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    @RequestMapping("update")
    ResponseEntity<Map<String,Object>> update(@RequestBody UpdateHomeAirportParam param){

        Long result = homeAirportService.update(param);

        Map<String,Object> m = new HashMap<>();

        m.put("success", true);

        m.put("result",result);

        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    @RequestMapping("delete/{id}")
    ResponseEntity<Map<String,Object>>delete(@PathVariable("id") Long id){

        homeAirportService.delete(id);

        Map<String,Object> m = new HashMap<>();

        m.put("success", true);

        return new ResponseEntity<>(m, HttpStatus.OK);
    }

}
