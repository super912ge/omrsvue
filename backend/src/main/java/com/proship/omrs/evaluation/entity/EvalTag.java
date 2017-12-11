package com.proship.omrs.evaluation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.base.entity.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class EvalTag extends BaseEntity{

    @Id
    private Long id ;
    @JsonIgnore
    private Long uuid ;
    @JsonIgnore
    private Long creatorId ;
    @JsonIgnore
    private Long destroyerId ;

    @JsonIgnore
    private Long evalTagTypeId;

    @Transient
    @JsonIgnore
    private EvalTagType type;

    @Transient
    private Long discriminatorId;

    @Transient
    private String label;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private EvalTag parent;

    @OneToMany(mappedBy = "parent")
    private List <EvalTag> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public EvalTagType getType() {

        this.type = EvalTagTypeMap.getEvalTagType(this.evalTagTypeId);
        return type;
    }

    public void setType(EvalTagType type) {
        this.type = type;
    }

    public Long getDiscriminatorId() {

        this.discriminatorId = EvalTagTypeMap.getEvalTagType(this.evalTagTypeId).getDiscriminatorId();
        return discriminatorId;
    }

    public void setDiscriminatorId(Long discriminatorId) {
        this.discriminatorId = discriminatorId;
    }

    public Long getDestroyerId() {
        return destroyerId;
    }

    public void setDestroyerId(Long destroyerId) {
        this.destroyerId = destroyerId;
    }


    public Long getEvalTagTypeId() {
        return evalTagTypeId;
    }

    public void setEvalTagTypeId(Long evalTagTypeId) {
        this.evalTagTypeId = evalTagTypeId;
    }

    public EvalTag getParent() {
        return parent;
    }

    public void setParent(EvalTag parent) {
        this.parent = parent;
    }

    public List<EvalTag> getChildren() {
        return children;
    }

    public void setChildren(List<EvalTag> children) {
        this.children = children;
    }

    public String getLabel() {
        if (this.type!=null)
        this.label = this.type.getLabel();
        else this.label = EvalTagTypeMap.getEvalTagType(this.evalTagTypeId).getLabel();
        this.type = getType();
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}