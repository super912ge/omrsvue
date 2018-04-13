package com.proship.omrs.document.passport.controller;

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

@RequestMapping("/passport/")
@RestController
public class PassportController{

    
    @Autowired
    @Qualifier("passportService")
    DocumentService passportService;

    @RequestMapping(value = "create/{candidateId}",method = RequestMethod.POST)
    @ApiOperation(value = "Create a new passport for candidate and return the created passport's id.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful creating new passport. ", response = Long.class),
            @ApiResponse(code = 409, message = "Illegal argument",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error",response = ErrorDetail.class)})
    public ResponseEntity<Long> createDocument(@PathVariable("candidateId")Long id,
                                                             @RequestBody CreateEditDocumentParam param){

        Long passportId = passportService.create(id,param);

       return new ResponseEntity<>(passportId, HttpStatus.CREATED);
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ApiOperation(value = "Update an existing passport document and return the new document id.",
            notes = " Update the target document by logically deleting it and creating a new document. " +
                    "Return the new created document's id.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful creating new passport. ", response = Long.class),
            @ApiResponse(code = 409, message = "Illegal argument.",response = ErrorDetail.class),
            @ApiResponse(code = 404, message = "Resource not found.",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error.",response = ErrorDetail.class)})
    public ResponseEntity<Long> updateDocument( @RequestBody CreateEditDocumentParam param){

        Long passportId = passportService.update(param);

      return new ResponseEntity<>(passportId,HttpStatus.CREATED);
    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    @ApiOperation("Delete the passport with the given id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful creating new passport. ", response = Long.class),
            @ApiResponse(code = 409, message = "Illegal argument",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error",response = ErrorDetail.class)})
    public ResponseEntity<Long> deleteDocument(@PathVariable("id")Long id){

         passportService.delete(id);

        return new ResponseEntity<>(id,HttpStatus.OK);
    }
}
