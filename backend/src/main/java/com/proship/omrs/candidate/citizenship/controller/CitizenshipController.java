package com.proship.omrs.candidate.citizenship.controller;

import com.proship.omrs.candidate.citizenship.entity.ParticipantCitizenship;
import com.proship.omrs.candidate.citizenship.service.CitizenshipService;
import com.proship.omrs.candidate.citizenship.param.UpdateCitizenshipParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/citizenship/")
public class CitizenshipController {

    @Autowired
    CitizenshipService citizenshipService;


    @RequestMapping(value = "create/{id}", method = RequestMethod.POST)
    ResponseEntity<Map<String,Object>> addCitizenships(@PathVariable("id") Long id, @RequestBody List<Long> ids){

        List<ParticipantCitizenship> result = citizenshipService.addCitizenship(id,ids);

        Map<String,Object> m = new HashMap<>();

        m.put("success", true);

        m.put("result",result);

        return new ResponseEntity<Map<String, Object>>(m, HttpStatus.OK);

    }

    @RequestMapping(value = "update/{id}",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>>updateCitizenship(@PathVariable("id") Long candidate,
                                                               @RequestBody List<Long>countryIds){

        List<ParticipantCitizenship> ids = citizenshipService.update(candidate,countryIds);

        Map<String, Object> result = new HashMap<>();

        result.put("success",true);

        result.put("result",ids);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>>deleteCitizenship(@RequestBody List<Long> ids){

        citizenshipService.delete(ids);

        Map<String, Object> result = new HashMap<>();

        result.put("success",true);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>>deleteByCandidateId(@PathVariable("id") Long id){

        citizenshipService.delete(id);

        Map<String, Object> result = new HashMap<>();

        result.put("success",true);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
