package com.proship.omrs.evaluation.controller;

import com.proship.omrs.base.controller.BaseController;
import com.proship.omrs.evaluation.entity.EvalTag;
import com.proship.omrs.evaluation.repository.EvalTagRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/evaluation/candidate")
public class EvaluationController extends BaseController<EvalTag,Long>{

    public EvaluationController(EvalTagRepository repo) {
        super(repo);
    }
}
