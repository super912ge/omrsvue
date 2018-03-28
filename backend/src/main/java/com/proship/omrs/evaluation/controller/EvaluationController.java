package com.proship.omrs.evaluation.controller;

import com.proship.omrs.evaluation.entity.EvalTag;
import com.proship.omrs.evaluation.param.NewRatingParam;

import com.proship.omrs.evaluation.service.EvaluationTagService;
import com.proship.omrs.utils.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/evaluation/")
public class EvaluationController {

    @Autowired
    EvaluationTagService evaluationTagService;

    @RequestMapping(value = "create/{id}",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> createNewRating(@PathVariable("id")Long id, @RequestBody NewRatingParam param){

        EvalTag tag = evaluationTagService.createNewRating(id, param );

        return Utils.getResponseEntityWithResultMap(tag);
    }

}
