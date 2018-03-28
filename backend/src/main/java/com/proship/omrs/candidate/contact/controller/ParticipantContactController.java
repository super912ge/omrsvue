package com.proship.omrs.candidate.contact.controller;

import com.proship.omrs.candidate.contact.param.CreateContactParam;
import com.proship.omrs.candidate.contact.param.UpdateContactParam;
import com.proship.omrs.candidate.contact.service.ContactService;
import com.proship.omrs.utils.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/contact/")
public class ParticipantContactController  {

    @Autowired
    ContactService contactService;

    @RequestMapping(value = "create/{id}",method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createContact(@PathVariable("id")Long id, @RequestBody CreateContactParam param){

        Long contactId = contactService.create(id,param);

        return Utils.getResponseEntityWithResultMap(contactId);
    }


    @RequestMapping(value = "update",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>>updateContact(@RequestBody UpdateContactParam param){

        Long contactId = contactService.update(param);

        return Utils.getResponseEntityWithResultMap(contactId);

    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>>updateContact(@PathVariable("id")Long id){

        contactService.delete(id);

        return Utils.getResponseEntityWithResultMap(id);
    }
}
