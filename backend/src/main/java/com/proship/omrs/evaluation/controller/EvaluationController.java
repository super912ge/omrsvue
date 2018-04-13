package com.proship.omrs.evaluation.controller;

import com.proship.omrs.evaluation.entity.EvalTag;
import com.proship.omrs.evaluation.param.NewRatingParam;
import com.proship.omrs.evaluation.service.EvaluationTagService;
import com.proship.omrs.exceptions.ErrorDetail;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/evaluation/")
public class EvaluationController {

    @Autowired
    EvaluationTagService evaluationTagService;

    @RequestMapping(value = "create/{candidateId}",method = RequestMethod.POST)
    @ApiOperation(value = "Create a new evaluation tag for candidate and return the created evaluation tag's id.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful creating new evaluation tag. ", response = Long.class),
            @ApiResponse(code = 409, message = "Illegal argument",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error",response = ErrorDetail.class)})
    public ResponseEntity<EvalTag> createNewRating(@PathVariable("candidateId")Long id, @RequestBody NewRatingParam param){

        EvalTag tag = evaluationTagService.createNewRating(id, param );

        return new ResponseEntity<>(tag, HttpStatus.CREATED);
    }

}
