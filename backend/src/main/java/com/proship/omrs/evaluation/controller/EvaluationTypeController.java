package com.proship.omrs.evaluation.controller;

import com.proship.omrs.evaluation.entity.EvalTagType;
import com.proship.omrs.evaluation.entity.EvaluationTreeMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/evaluation/type")
@RestController
public class EvaluationTypeController {

    @RequestMapping(value = "/subtypes/{id}",  method= RequestMethod.GET, produces = {"application/json"})
    ResponseEntity<List<EvalTagType>> getSubtypes(@PathVariable long id){

        return new ResponseEntity<>(EvaluationTreeMap.getEvalTagTypeSubtypes(id), HttpStatus.OK);
    }

}
