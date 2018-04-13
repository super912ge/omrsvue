package com.proship.omrs.candidate.residency.controller;


import com.proship.omrs.candidate.residency.entity.ParticipantResidency;
import com.proship.omrs.candidate.residency.service.ResidencyService;
import com.proship.omrs.exceptions.ErrorDetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/residency/")
@Api(value = "Candidate's Residency", description = "Candidate's Residency API")
public class ParticipantResidencyController {

    @Autowired
    ResidencyService residencyService;

    @RequestMapping(value = "create/{candidateId}",method = RequestMethod.POST)
    @ApiOperation(value = "Add one or multiple new residency info for the given candidate and return the a list of created residency entities.",
            response=List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully creating residency info. ", response = List.class),
            @ApiResponse(code = 409, message = "Illegal argument.",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error.",response = ErrorDetail.class)})
    public ResponseEntity<List<ParticipantResidency>> addResidency(@PathVariable("candidateId")Long id,
                                                                   @RequestBody List<Long>ids){

        List<ParticipantResidency> result = residencyService.addResidency(id,ids);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "update/{id}",method = RequestMethod.POST)
    @ApiOperation(value = "Update residency info for the given candidate and return a list of valid residency entities.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully updating residency info. ", response = List.class),
            @ApiResponse(code = 409, message = "Illegal argument.",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error.",response = ErrorDetail.class)})
    public ResponseEntity< List<ParticipantResidency>>update(@PathVariable("id")Long candidateId, @RequestBody List<Long> countryIds){

        List<ParticipantResidency> result = residencyService.update(candidateId, countryIds);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }



    @RequestMapping(value = "delete",method = RequestMethod.POST)
    @ApiOperation(value = "Delete the residency info by given ids and return the deleted residency's ids.",
            notes = "The data will only be logically deleted.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleting a new address. ", response = Long.class),
            @ApiResponse(code = 409, message = "Illegal argument.",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error.",response = ErrorDetail.class)})
    public ResponseEntity<List<Long>>delete(@RequestBody List<Long> ids){

        residencyService.delete(ids);

        return new ResponseEntity<>(ids, HttpStatus.OK);
    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    @ApiOperation(value = "Delete all the residency info of the candidate with the given id and return the candidate's id.",
            notes = "The data will only be logically deleted.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleting the given candidate's residency.", response = Long.class),
            @ApiResponse(code = 409, message = "Illegal argument.",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error.",response = ErrorDetail.class)})
    public ResponseEntity<Long>deleteByCandidateId(@PathVariable("id") Long id){

        residencyService.delete(id);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }



}
