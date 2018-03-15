package com.proship.omrs.candidate.base.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.candidate.participant.entity.Participant;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@MappedSuperclass
public class BaseOverrideEntity {


    @JsonIgnore
    private Long destroyerId;

    @JsonIgnore
    private Timestamp nexttransactiontime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Participant participant;

    public Long getDestroyerId() {
        return destroyerId;
    }

    public void setDestroyerId(Long destroyerId) {
        this.destroyerId = destroyerId;
    }

    public Timestamp getNexttransactiontime() {
        return nexttransactiontime;
    }

    public void setNexttransactiontime(Timestamp nexttransactiontime) {
        this.nexttransactiontime = nexttransactiontime;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

}
