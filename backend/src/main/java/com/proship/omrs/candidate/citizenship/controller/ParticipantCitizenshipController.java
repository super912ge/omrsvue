package com.proship.omrs.candidate.citizenship.controller;

import com.proship.omrs.candidate.citizenship.entity.ParticipantCitizenship;
import com.proship.omrs.candidate.citizenship.service.CitizenshipService;
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
@ControllerAdvice
@RequestMapping("/citizenship/")
@Api(value = "Candidate's Citizenship", description = "Candidate's Citizenship API")
public class ParticipantCitizenshipController {

    @Autowired
    CitizenshipService citizenshipService;

    @RequestMapping(value = "create/{candidateId}", method = RequestMethod.POST)
    @ApiOperation(value = "Add one or multiple new citizenship for the given candidate and return the a list of created citizenship entities.",
            response=List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully creating citizenship info. ", response = List.class),
            @ApiResponse(code = 409, message = "Illegal argument.",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error.",response = ErrorDetail.class)})
        ResponseEntity<List<ParticipantCitizenship>> addCitizenships(@PathVariable("candidateId") Long id, @RequestBody List<Long> countryIds){

        List<ParticipantCitizenship> result = citizenshipService.addCitizenship(id,countryIds);

        return new ResponseEntity<>(result,HttpStatus.CREATED);

    }

    @RequestMapping(value = "update/{candidateId}",method = RequestMethod.POST)
    @ApiOperation(value = "Update citizenship info for the given candidate and return a list of valid citizenship entities.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully updating citizenship info. ", response = List.class),
            @ApiResponse(code = 409, message = "Illegal argument.",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error.",response = ErrorDetail.class)})
    public ResponseEntity<List<ParticipantCitizenship>>updateCitizenship(@PathVariable("candidateId") Long candidate,
                                                               @RequestBody List<Long>countryIds){

        List<ParticipantCitizenship> result = citizenshipService.update(candidate,countryIds);

        return new ResponseEntity<>(result,HttpStatus.CREATED);
    }

    @RequestMapping(value = "delete",method = RequestMethod.POST)
    @ApiOperation(value = "Delete the citizenship info by given ids and return the deleted citizenship's ids.",
            notes = "The data will only be logically deleted.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleting a new address. ", response = Long.class),
            @ApiResponse(code = 409, message = "Illegal argument.",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error.",response = ErrorDetail.class)})
    public ResponseEntity<List<Long>>deleteCitizenship(@RequestBody List<Long> ids){

        citizenshipService.delete(ids);

        return new ResponseEntity<>(ids,HttpStatus.CREATED);
    }

    @RequestMapping(value = "delete/{candidateId}",method = RequestMethod.GET)
    @ApiOperation(value = "Delete all the citizenship info of the candidate with the given id and return the candidate's id.",
            notes = "The data will only be logically deleted.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleting the given candidate's citizenship.", response = Long.class),
            @ApiResponse(code = 409, message = "Illegal argument.",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error.",response = ErrorDetail.class)})
    public ResponseEntity<Long>deleteByCandidateId(@PathVariable("candidateId") Long id){

        citizenshipService.delete(id);

        return new ResponseEntity<>(id,HttpStatus.CREATED);
    }
}
