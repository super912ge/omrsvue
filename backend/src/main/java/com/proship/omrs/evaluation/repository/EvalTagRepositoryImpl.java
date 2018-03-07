package com.proship.omrs.evaluation.repository;

import com.proship.omrs.evaluation.entity.EvalTag;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EvalTagRepositoryImpl implements EvalTagCustomRepository{

    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    @Override
    public void refresh(EvalTag evalTag) {
        entityManager.refresh(evalTag);
    }
}
