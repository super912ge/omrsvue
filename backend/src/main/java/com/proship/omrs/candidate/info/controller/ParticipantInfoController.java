package com.proship.omrs.candidate.info.controller;


import com.proship.omrs.candidate.info.param.CreateInfoParam;
import com.proship.omrs.candidate.info.param.UpdateInfoParam;
import com.proship.omrs.candidate.info.service.ParticipantInfoService;
import com.proship.omrs.exceptions.ErrorDetail;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info/")
public class ParticipantInfoController {

    @Autowired
    ParticipantInfoService participantInfoService;


    @ApiOperation(value = "Add new extra info for candidate and return the created extra info's id.")
    @RequestMapping(value = "create/{candidateId}", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully creating a new extra info. ", response = Long.class),
            @ApiResponse(code = 409, message = "Illegal argument",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error",response = ErrorDetail.class)})
    public ResponseEntity<Long>addInfo(@PathVariable("candidateId")Long id, CreateInfoParam param){

        Long result = participantInfoService.addNewInfo(id,param);

        return new ResponseEntity<>(result,HttpStatus.CREATED);

    }
    
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ApiOperation(value = "Update the extra info for candidate and return the new extra info's id." )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully updating a new extra info. ", response = Long.class),
            @ApiResponse(code = 409, message = "Illegal argument",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error",response = ErrorDetail.class)})
    public ResponseEntity<Long>update(UpdateInfoParam param){

        Long id = participantInfoService.update(param);

        return new ResponseEntity<>(id,HttpStatus.CREATED);

    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    @ApiOperation(value = "Delete the extra info and return the deleted extra info's id.",
            notes = "The information will only be logically deleted.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleting a new extra info. ", response = Long.class),
            @ApiResponse(code = 409, message = "Illegal argument.",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error.",response = ErrorDetail.class)})
    public ResponseEntity<Long>delete(@PathVariable("id") Long id){

        participantInfoService.delete(id);

        return new ResponseEntity<>(id,HttpStatus.OK);

    }
}
