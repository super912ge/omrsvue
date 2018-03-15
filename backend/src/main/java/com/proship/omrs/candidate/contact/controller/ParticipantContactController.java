package com.proship.omrs.candidate.contact.controller;

import com.proship.omrs.candidate.contact.param.CreateContactParam;
import com.proship.omrs.candidate.contact.param.UpdateContactParam;
import com.proship.omrs.candidate.contact.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/contact/")
public class ParticipantContactController  {

    @Autowired
    ContactService contactService;

    @RequestMapping("create/{id}")
    public ResponseEntity<Map<String, Object>> createContact(@PathVariable("id")Long id, @RequestBody CreateContactParam param){

        Long contactId = contactService.create(id,param);

        Map<String, Object> result = new HashMap<>();

        result.put("success",true);

        result.put("result", contactId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @RequestMapping("update")
    public ResponseEntity<Map<String,Object>>updateContact(@RequestBody UpdateContactParam param){

        Long contactId = contactService.update(param);

        Map<String, Object> result = new HashMap<>();

        result.put("success",true);

        result.put("result",contactId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping("delete/{id}")
    public ResponseEntity<Map<String,Object>>updateContact(@PathVariable("id")Long id){

        contactService.delete(id);

        Map<String, Object> result = new HashMap<>();

        result.put("success",true);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
