package com.proship.omrs.evaluation.entity;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.proship.omrs.evaluation.repository.EvalTagTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class EvalTagTypeMap implements InitializingBean {

    static final Logger logger = LoggerFactory.getLogger(EvalTagTypeMap.class);
    private static BiMap<Long, EvalTagType> evalTagTypes;

    @Autowired
    EvalTagTypeRepository repo;

    @Override
    public void afterPropertiesSet() throws Exception {

        logger.info("[evalTagTypeMap][afterPropertiesSet]start");
        List<EvalTagType> list = repo.findAll();

        evalTagTypes = HashBiMap.create();

        for (EvalTagType evalTagType : list) {
            evalTagTypes.put(evalTagType.getId(), evalTagType);
        }

        logger.info("[evalTagTypeMap][afterPropertiesSet]finish setting " + evalTagTypes.size() + " evalTagType types.");
    }

    public static Map getEvalTagTypes() {
        return evalTagTypes;
    }


    public static EvalTagType getEvalTagType(Long id) {
        return evalTagTypes.get(id);
    }
}
