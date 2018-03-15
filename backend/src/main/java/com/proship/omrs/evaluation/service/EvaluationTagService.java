package com.proship.omrs.evaluation.service;

import com.proship.omrs.evaluation.entity.EvalTag;
import com.proship.omrs.evaluation.param.EvaluationSearchParam;
import com.proship.omrs.evaluation.param.NewRatingParam;

import java.util.List;
import java.util.Set;

public interface EvaluationTagService {


    Set<Long> findEvaluationByCriteria(EvaluationSearchParam param);

    EvalTag createNewRating(Long id, NewRatingParam param);

    EvalTag findEvalTagById(Long id);
}
