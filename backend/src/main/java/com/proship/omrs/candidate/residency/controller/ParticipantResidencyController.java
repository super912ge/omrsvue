package com.proship.omrs.candidate.residency.controller;


import com.proship.omrs.candidate.residency.entity.ParticipantResidency;
import com.proship.omrs.candidate.residency.service.ResidencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/residency/")
public class ParticipantResidencyController {

    @Autowired
    ResidencyService residencyService;

    @RequestMapping("create/{id}")
    public ResponseEntity<Map<String,Object>> addResidency(@PathVariable("id")Long id, @RequestBody List<Long>ids){

        List<ParticipantResidency> result = residencyService.addResidency(id,ids);

        Map<String,Object> map = new HashMap<>();

        map.put("success",true);

        map.put("result", result);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @RequestMapping("update/{id}")
    public ResponseEntity<Map<String,Object>>update(@PathVariable("id")Long candidateId, @RequestBody List<Long> countryIds){

        List<ParticipantResidency> ids = residencyService.update(candidateId, countryIds);

        Map<String, Object> result = new HashMap<>();

        result.put("success",true);

        result.put("result",ids);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }



    @RequestMapping("delete")
    public ResponseEntity<Map<String,Object>>delete(@RequestBody List<Long> ids){

        residencyService.delete(ids);

        Map<String, Object> result = new HashMap<>();

        result.put("success",true);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>>deleteByCandidateId(@PathVariable("id") Long id){

        residencyService.delete(id);

        Map<String, Object> result = new HashMap<>();

        result.put("success",true);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }



}
