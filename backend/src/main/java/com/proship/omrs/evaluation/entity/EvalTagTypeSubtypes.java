package com.proship.omrs.evaluation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.base.entity.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
@Table(name = "eval_tag_type_subtypes")
@IdClass(EvalTagTypeSubtypes.class)
public class EvalTagTypeSubtypes implements Serializable{

    @Id
    @Column(name = "eval_tag_type_id")
    private Long evalTagTypeId;

    @Id
    @Column(name = "creator_id")
    @JsonIgnore
    private Long creatorId;

    @Id
    private Long value;

    @Id
    @JsonIgnore
    @Column(name = "destroyer_id")
    private Long destroyerId;

    @Id
    @JsonIgnore
    private Timestamp transactiontime;

    @Id
    @JsonIgnore
    private Timestamp nexttransactiontime;

    public Long getEvalTagTypeId() {
        return evalTagTypeId;
    }

    public void setEvalTagTypeId(Long evalTagTypeId) {
        this.evalTagTypeId = evalTagTypeId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Long getDestroyerId() {
        return destroyerId;
    }

    public void setDestroyerId(Long destroyerId) {
        this.destroyerId = destroyerId;
    }

    public Timestamp getTransactiontime() {
        return transactiontime;
    }

    public void setTransactiontime(Timestamp transactiontime) {
        this.transactiontime = transactiontime;
    }

    public Timestamp getNexttransactiontime() {
        return nexttransactiontime;
    }

    public void setNexttransactiontime(Timestamp nexttransactiontime) {
        this.nexttransactiontime = nexttransactiontime;
    }
}
