package com.proship.omrs.candidate.candidate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Where(clause = "nexttransactiontime > current_date")
public class ParticipantEventTypeTts {


    @Id
    @JsonIgnore
    private Long id;

    @ManyToOne
    private ParticipantEventType participantEventType;

    @JsonIgnore
    private Long creatorId;

    @JsonIgnore
    private Date transactiontime ;

    @JsonIgnore
    private Date nexttransactiontime ;

    @JsonIgnore
    @ManyToOne
    private ParticipantEvent participantEvent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ParticipantEventType getParticipantEventType() {
        return participantEventType;
    }

    public void setParticipantEventType(ParticipantEventType participantEventType) {
        this.participantEventType = participantEventType;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Date getTransactiontime() {
        return transactiontime;
    }

    public void setTransactiontime(Date transactiontime) {
        this.transactiontime = transactiontime;
    }

    public Date getNexttransactiontime() {
        return nexttransactiontime;
    }

    public void setNexttransactiontime(Date nexttransactiontime) {
        this.nexttransactiontime = nexttransactiontime;
    }

    public ParticipantEvent getParticipantEvent() {
        return participantEvent;
    }

    public void setParticipantEvent(ParticipantEvent participantEvent) {
        this.participantEvent = participantEvent;
    }
}
