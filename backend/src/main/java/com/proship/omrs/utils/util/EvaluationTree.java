package com.proship.omrs.utils.util;

import com.proship.omrs.evaluation.entity.EvalTagType;
import com.proship.omrs.evaluation.entity.EvalTagTypeMap;
import com.proship.omrs.evaluation.entity.EvalTagTypeSubtypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EvaluationTree {

    private HashMap<Long,EvaluationTree> evaluationMap;
    private EvalTagType data = null;
    private Long id = null;
    private List<Long> children = new ArrayList<>();
    private Long parentId;



    public EvaluationTree(EvalTagTypeSubtypes data, HashMap<Long,EvaluationTree> evaluationMap,Long parentId) {

        if (!evaluationMap.containsKey(data.getEvalTagTypeId())) {

            this.id = data.getEvalTagTypeId();
            this.data = EvalTagTypeMap.getEvalTagType(data.getEvalTagTypeId());
            this.evaluationMap = evaluationMap;
            this.addChild(EvalTagTypeMap.getEvalTagType(data.getValue()));
            this.parentId = parentId;
            evaluationMap.put(data.getEvalTagTypeId(),this);

        }else

        evaluationMap.get(data.getEvalTagTypeId()).addChild(EvalTagTypeMap.getEvalTagType(data.getValue()));
    }


    public EvaluationTree(EvalTagType data, HashMap<Long,EvaluationTree> evaluationMap,Long parentId) {

        if(!evaluationMap.containsKey(data.getId())) {

            this.id = data.getId();
            this.data = EvalTagTypeMap.getEvalTagType(data.getId());
            this.evaluationMap = evaluationMap;
            this.parentId = parentId;
            evaluationMap.put(data.getId(), this);
        }
    }


    public void addChild(EvalTagType child) {

        this.children.add(child.getId());

        if(!evaluationMap.containsKey(child.getId())){

            evaluationMap.put(child.getId(),new EvaluationTree(child,this.evaluationMap,this.id));
        }else {
            EvaluationTree childNode = evaluationMap.get(child.getId());
            childNode.setParentId(this.id);
            evaluationMap.put(child.getId(), childNode);
        }
    }

    public void addChildren(List<EvalTagType> children) {

        for(EvalTagType child : children) {
            this.addChild(child);
        }
    }

    public List<Long> getChildren() {
        return children;
    }

    public EvalTagType getData() {
        return data;
    }

    public void setData(EvalTagType data) {
        this.data = data;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}