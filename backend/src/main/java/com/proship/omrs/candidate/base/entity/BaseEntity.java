package com.proship.omrs.candidate.base.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@MappedSuperclass
public class BaseEntity {

    @JsonIgnore
    private Timestamp transactiontime;

    @JsonIgnore
    private Long creatorId;

    public Timestamp getTransactiontime() {
        return transactiontime;
    }

    public void setTransactiontime(Timestamp transactiontime) {
        this.transactiontime = transactiontime;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }


}
