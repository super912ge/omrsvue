package com.proship.omrs.base.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.user.entity.User;

import java.sql.Timestamp;

public class BaseEntityWithCreator {

    @JsonIgnore
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
    private User creator;


}
