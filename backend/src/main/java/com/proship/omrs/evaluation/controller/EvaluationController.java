package com.proship.omrs.evaluation.controller;

import com.proship.omrs.base.controller.BaseController;
import com.proship.omrs.evaluation.entity.EvalTag;
import com.proship.omrs.evaluation.param.NewRatingParam;
import com.proship.omrs.evaluation.repository.EvalTagRepository;
import com.proship.omrs.evaluation.service.EvaluationTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/evaluation/")
public class EvaluationController extends BaseController<EvalTag,Long>{

    @Autowired
    EvaluationTagService evaluationTagService;

    public EvaluationController(EvalTagRepository repo) {
        super(repo);
    }

    @RequestMapping("create/{id}")
    public ResponseEntity<EvalTag> createNewRating(@PathVariable("id")Long id, @RequestBody NewRatingParam param){

        EvalTag tag = evaluationTagService.createNewRating(id, param );

        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

}
