package com.proship.omrs.candidate.address.controller;

import com.proship.omrs.candidate.address.param.CreateAddressParam;
import com.proship.omrs.candidate.address.param.UpdateAddressParam;
import com.proship.omrs.candidate.address.service.AddressService;
import com.proship.omrs.exceptions.ErrorDetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address/")
@Api(value = "Candidate's Address", description = "Candidate's Address API")
public class ParticipantAddressController {


    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "Add new address info for candidate and return the created address's id.")
    @RequestMapping(value = "create/{candidateId}", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully creating a new address. ", response = Long.class),
            @ApiResponse(code = 409, message = "Illegal argument",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error",response = ErrorDetail.class)})
    public ResponseEntity<Long> addNewAddress(@PathVariable("candidateId") Long id, @RequestBody CreateAddressParam param){

        Long result = addressService.addAddress(id,param);

        return new ResponseEntity<>(result,HttpStatus.CREATED);

    }
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ApiOperation(value = "Update the address info for candidate and return the new address's id." )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully updating a new address. ", response = Long.class),
            @ApiResponse(code = 409, message = "Illegal argument",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error",response = ErrorDetail.class)})
    public ResponseEntity<Long>update(@RequestBody UpdateAddressParam param){

        Long id = addressService.update(param);

         return new ResponseEntity<>(id,HttpStatus.CREATED);
    }


    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    @ApiOperation(value = "Delete the address info and return the deleted address's id.",
            notes = "The information will only be logically deleted.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleting a new address. ", response = Long.class),
            @ApiResponse(code = 409, message = "Illegal argument.",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error.",response = ErrorDetail.class)})
    public ResponseEntity<Long>update(@PathVariable("id")Long id){

        addressService.delete(id);

        return new ResponseEntity<>(id,HttpStatus.OK);
    }
}
