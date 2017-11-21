package com.proship.omrs.evaluation.service;

import com.proship.omrs.evaluation.param.EvaluationSearchParam;

import java.util.List;
import java.util.Set;

public interface EvaluationTagService {


    Set<Long> findEvaluationByCriteria(EvaluationSearchParam param);
}
