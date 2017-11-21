package com.proship.omrs.evaluation.entity;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.proship.omrs.evaluation.repository.EvalTagTypeDiscriminatorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public class EvalTagTypeDiscriminatorMap implements InitializingBean{

    static final Logger logger = LoggerFactory.getLogger(EvalTagTypeDiscriminatorMap.class);
    private static BiMap<Long, EvalTagTypeDiscriminator> evalTagTypeDiscriminators;

    @Autowired
    EvalTagTypeDiscriminatorRepository repo;

    @Override
    public void afterPropertiesSet() throws Exception {

        logger.info("[evalTagTypeDiscriminatorMap][afterPropertiesSet]start");
        List<EvalTagTypeDiscriminator> list = repo.findAll();

        evalTagTypeDiscriminators = HashBiMap.create();

        for (EvalTagTypeDiscriminator evalTagTypeDiscriminator : list) {
            evalTagTypeDiscriminators.put(evalTagTypeDiscriminator.getId(), evalTagTypeDiscriminator);
        }

        logger.info("[evalTagTypeDiscriminatorMap][afterPropertiesSet]finish setting " + evalTagTypeDiscriminators.size() + " evalTagTypeDiscriminator types.");
    }

    public static Map getEvalTagTypeDiscriminators() {
        return evalTagTypeDiscriminators;
    }

    ;

    public static EvalTagTypeDiscriminator getEvalTagTypeDiscriminator(Long id) {
        return evalTagTypeDiscriminators.get(id);
    }
}
