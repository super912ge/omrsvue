package com.proship.omrs.candidate.candidate.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ParticipantEventTemporal {
    @JsonIgnore
    @Id
    private Long id;

    @JsonIgnore
    private Date transactiontime ;

    private Long creatorId;

    @OneToOne @JoinColumn(name = "participantEventId")
    ParticipantEvent participantEvent;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTransactiontime() {
        return transactiontime;
    }

    public void setTransactiontime(Date transactiontime) {
        this.transactiontime = transactiontime;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public ParticipantEvent getParticipantEvent() {
        return participantEvent;
    }

    public void setParticipantEvent(ParticipantEvent participantEvent) {
        this.participantEvent = participantEvent;
    }


}
