package com.proship.omrs.base.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.user.entity.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;
@MappedSuperclass
public class BaseEntityWithCreator {

    @JsonIgnore
    @CreationTimestamp
    private Timestamp transactiontime;

    @JsonIgnore
    private Timestamp nexttransactiontime;

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


    private Long creatorId;

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
}
