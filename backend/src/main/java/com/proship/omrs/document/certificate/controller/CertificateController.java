package com.proship.omrs.document.certificate.controller;

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
@RequestMapping(value="/certificate/")
@CrossOrigin
public class CertificateController {


    @Autowired
    @Qualifier("certificateService")
    DocumentService certificateService;

    @RequestMapping(value = "create/{candidateId}",method = RequestMethod.POST)
    @ApiOperation(value = "Create a new certificate for candidate and return the created certificate's id.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful creating new certificate. ", response = Long.class),
            @ApiResponse(code = 409, message = "Illegal argument",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error",response = ErrorDetail.class)})
    public ResponseEntity<Long>createDocument(@PathVariable("candidateId")Long id,
            @RequestBody CreateEditDocumentParam param){

        Long certificateId = certificateService.create(id,param);

        return new ResponseEntity<>(certificateId, HttpStatus.CREATED);
        
    }
    @ApiOperation(value = "Update an existing certificate and return the new document id.",
            notes = " Update the target document by logically deleting it and creating a new document. " +
            "Return the new created document's id.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful creating new certificate. ", response = Long.class),
            @ApiResponse(code = 409, message = "Illegal argument",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error",response = ErrorDetail.class)})
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public ResponseEntity<Long> updateDocument(
            @RequestBody CreateEditDocumentParam param){

        Long certificateId = certificateService.update(param);

        return new ResponseEntity<>(certificateId, HttpStatus.CREATED);
    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    @ApiOperation("Delete the certificate with the given id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful creating new certificate. ", response = Long.class),
            @ApiResponse(code = 409, message = "Illegal argument",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error",response = ErrorDetail.class)})
    public ResponseEntity<Long> deleteDocument(@PathVariable("id")Long id){

        certificateService.delete(id);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
