package com.proship.omrs.evaluation.entity;

import com.proship.omrs.evaluation.repository.EvalTagTypeSubtypesRepository;
import com.proship.omrs.utils.util.EvaluationTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class EvaluationTreeMap implements InitializingBean {


    static final Logger logger = LoggerFactory.getLogger(EvaluationTreeMap.class);
    private static HashMap<Long, EvaluationTree> evalTagTypeTreeMap;

    @Autowired
    EvalTagTypeSubtypesRepository repo;

    @Override
    public void afterPropertiesSet() throws Exception {

        logger.info("[evalTagTypeTreeMap][afterPropertiesSet]start");
        List<EvalTagTypeSubtypes> list = repo.findAll();

        evalTagTypeTreeMap = new HashMap<>();

        for (EvalTagTypeSubtypes evalTagTypeSubtypes : list) {

            if (evalTagTypeSubtypes.getNexttransactiontime().after(new Timestamp(System.currentTimeMillis()))) {

                EvaluationTree tree = new EvaluationTree(evalTagTypeSubtypes, evalTagTypeTreeMap,null);
            }
        }

        logger.info("[evalTagTypeTreeMap][afterPropertiesSet]finish setting " + evalTagTypeTreeMap.size() + " evalTagTypeSubtypes types.");
    }

    public static Map getEvalTagTypeSubtypess() {
        return evalTagTypeTreeMap;
    }

    private static EvaluationTree getTree(Long id){
        return evalTagTypeTreeMap.get(id);
    }

    public static List<EvalTagType> getEvalTagTypeSubtypes(Long id) {

        List<EvalTagType> children = new ArrayList<>();

        for (Long childId : evalTagTypeTreeMap.get(id).getChildren()){

            EvalTagType child = EvalTagTypeMap.getEvalTagType(childId);
            child.setParentId(id);
            children.add(child);
        }

        return children;
    }
}
