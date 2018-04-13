package com.proship.omrs.candidate.homeAirport.controller;

import com.proship.omrs.candidate.homeAirport.param.CreateHomeAirportParam;
import com.proship.omrs.candidate.homeAirport.param.UpdateHomeAirportParam;
import com.proship.omrs.candidate.homeAirport.service.HomeAirportService;
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
import java.util.Map;

@RestController
@RequestMapping("/homeAirport/")
@Api(description = "candidate's home airport API")
public class ParticipantHomeAirportController {

    @Autowired
    HomeAirportService homeAirportService;


    @ApiOperation(value = "Add new home airport info for candidate and return the created home airport's id.")
    @RequestMapping(value = "create/{candidateId}", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully creating a new home airport. ", response = Long.class),
            @ApiResponse(code = 409, message = "Illegal argument",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error",response = ErrorDetail.class)})
    ResponseEntity<Long> create(@PathVariable("candidateId")Long id, @RequestBody CreateHomeAirportParam param){

       Long result = homeAirportService.create(id,param);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ApiOperation(value = "Update the home airport info for candidate and return the new home airport's id." )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully updating a new home airport. ", response = Long.class),
            @ApiResponse(code = 409, message = "Illegal argument",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error",response = ErrorDetail.class)})
    ResponseEntity<Long> update(@RequestBody UpdateHomeAirportParam param){

        Long result = homeAirportService.update(param);

        return new ResponseEntity<>(result,HttpStatus.CREATED);
    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    @ApiOperation(value = "Delete the home airport info and return the deleted home airport's id.",
            notes = "The information will only be logically deleted.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleting a new home airport. ", response = Long.class),
            @ApiResponse(code = 409, message = "Illegal argument.",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error.",response = ErrorDetail.class)})
    ResponseEntity<Long>delete(@PathVariable("id") Long id){

        homeAirportService.delete(id);

        return new ResponseEntity<>(id,HttpStatus.CREATED);
    }

}
