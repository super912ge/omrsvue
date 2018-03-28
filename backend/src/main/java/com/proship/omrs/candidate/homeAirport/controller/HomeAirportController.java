package com.proship.omrs.candidate.homeAirport.controller;

import com.proship.omrs.candidate.homeAirport.param.CreateHomeAirportParam;
import com.proship.omrs.candidate.homeAirport.param.UpdateHomeAirportParam;
import com.proship.omrs.candidate.homeAirport.service.HomeAirportService;
import com.proship.omrs.utils.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/homeAirport/")
public class HomeAirportController {

    @Autowired
    HomeAirportService homeAirportService;

    @RequestMapping(value = "/create/{id}",method = RequestMethod.POST)
    ResponseEntity<Map<String,Object>> create(@PathVariable("id")Long id, @RequestBody CreateHomeAirportParam param){

       Long result = homeAirportService.create(id,param);

        return Utils.getResponseEntityWithResultMap(result);
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    ResponseEntity<Map<String,Object>> update(@RequestBody UpdateHomeAirportParam param){

        Long result = homeAirportService.update(param);

        return Utils.getResponseEntityWithResultMap(result);
    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    ResponseEntity<Map<String,Object>>delete(@PathVariable("id") Long id){

        homeAirportService.delete(id);

        return Utils.getResponseEntityWithResultMap(id);
    }

}
