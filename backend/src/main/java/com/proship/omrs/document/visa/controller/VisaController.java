package com.proship.omrs.document.visa.controller;

import com.proship.omrs.document.base.param.CreateEditDocumentParam;
import com.proship.omrs.document.base.service.DocumentService;
import com.proship.omrs.exceptions.ErrorDetail;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/visa/")
public class VisaController  {


    @Autowired
    @Qualifier("visaService")
    DocumentService visaService;

    @RequestMapping(value = "create/{candidateId}",method = RequestMethod.POST)
    @ApiOperation(value = "Create a new visa for candidate and return the created visa's id.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful creating new visa. ", response = Long.class),
            @ApiResponse(code = 409, message = "Illegal argument",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error",response = ErrorDetail.class)})
    public ResponseEntity<Long> createDocument(@PathVariable("id")Long id,
                                                             @RequestBody CreateEditDocumentParam param){
        Long visaId = visaService.create(id,param);

        return new ResponseEntity<>(visaId, HttpStatus.CREATED);

    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ApiOperation(value = "Update an existing visa and return the new document id.",
            notes = " Update the target document by logically deleting it and creating a new document. " +
                    "Return the new created document's id.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful creating new visa. ", response = Long.class),
            @ApiResponse(code = 404,message = "Resource not found.", response = ErrorDetail.class),
            @ApiResponse(code = 409, message = "Illegal argument",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error",response = ErrorDetail.class)})
    public ResponseEntity<Long> updateDocument(
            @RequestBody CreateEditDocumentParam param){

        Long visaId = visaService.update(param);

        return new ResponseEntity<>(visaId, HttpStatus.CREATED);
    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    @ApiOperation("Delete the visa with the given id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful creating new visa. ", response = Long.class),
            @ApiResponse(code = 409, message = "Illegal argument",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error",response = ErrorDetail.class)})
    public ResponseEntity<Long> deleteDocument(@PathVariable("id")Long id){

        visaService.delete(id);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
