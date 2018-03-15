package com.proship.omrs.candidate.info.controller;


import com.proship.omrs.candidate.info.param.CreateInfoParam;
import com.proship.omrs.candidate.info.param.UpdateInfoParam;
import com.proship.omrs.candidate.info.service.ParticipantInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/info/")
public class ParticipantInfoController {

    @Autowired
    ParticipantInfoService participantInfoService;
    @RequestMapping("/create/{id}")
    public ResponseEntity<Map<String,Object>>addInfo(@PathVariable("id")Long id, CreateInfoParam param){

        Long result = participantInfoService.addNewInfo(id,param);

        Map<String,Object> map = new HashMap<>();

        map.put("success",true);

        map.put("result", result);

        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    @RequestMapping("update")
    public ResponseEntity<Map<String,Object>>update(UpdateInfoParam param){

        Long id = participantInfoService.update(param);

        Map<String,Object> map = new HashMap<>();

        map.put("success",true);

        map.put("result", id);

        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    @RequestMapping("delete/{id}")
    public ResponseEntity<Map<String,Object>>delete(@PathVariable("id") Long id){

        participantInfoService.delete(id);

        Map<String,Object> map = new HashMap<>();

        map.put("success",true);

        return new ResponseEntity<>(map, HttpStatus.OK);

    }
}
