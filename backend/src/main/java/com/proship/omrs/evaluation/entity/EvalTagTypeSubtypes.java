package com.proship.omrs.evaluation.entity;

import com.proship.omrs.base.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
@IdClass(EvalTagTypeSubtypes.class)
public class EvalTagTypeSubtypes implements Serializable{

    @Id
    private Long evalTagTypeId;
    @Id
    private Long creatorId;
    @Id
    private Long value;
    @Id
    private Long destroyerId;
    @Id
    private Timestamp transactiontime;
    @Id
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
