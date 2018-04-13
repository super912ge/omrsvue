package com.proship.omrs.candidate.contact.controller;

import com.proship.omrs.candidate.contact.param.CreateContactParam;
import com.proship.omrs.candidate.contact.param.UpdateContactParam;
import com.proship.omrs.candidate.contact.service.ContactService;
import com.proship.omrs.exceptions.ErrorDetail;
import com.proship.omrs.utils.util.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/contact/")
@Api(description = "candidate's contact info API")
public class ParticipantContactController  {

    @Autowired
    ContactService contactService;

    @ApiOperation(value = "Add new contact info info for candidate and return the created contact info's id.")
    @RequestMapping(value = "create/{candidateId}", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully creating a new contact info. ", response = Long.class),
            @ApiResponse(code = 409, message = "Illegal argument",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error",response = ErrorDetail.class)})
    public ResponseEntity<Long> createContact(@PathVariable("candidateId")Long id, @RequestBody CreateContactParam param){

        Long contactId = contactService.create(id,param);

        return new ResponseEntity<>(contactId,HttpStatus.CREATED);
    }


    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ApiOperation(value = "Update the contact info info for candidate and return the new contact info's id." )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully updating a new contact info. ", response = Long.class),
            @ApiResponse(code = 409, message = "Illegal argument",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error",response = ErrorDetail.class)})
    public ResponseEntity<Long>updateContact(@RequestBody UpdateContactParam param){

        Long contactId = contactService.update(param);

        return new ResponseEntity<>(contactId,HttpStatus.CREATED);

    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    @ApiOperation(value = "Delete the contact info info and return the deleted contact info's id.",
            notes = "The information will only be logically deleted.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleting a new contact info. ", response = Long.class),
            @ApiResponse(code = 409, message = "Illegal argument.",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error.",response = ErrorDetail.class)})
    public ResponseEntity<Long>updateContact(@PathVariable("id")Long id){

        contactService.delete(id);

        return new ResponseEntity<>(id,HttpStatus.OK);
    }
}
