package com.proship.omrs.candidate.event.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.proship.omrs.jsonviews.UserSerializer;
import com.proship.omrs.user.entity.User;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ParticipantEventTemporal {
    @JsonIgnore
    @Id
    private Long id;

    @JsonIgnore
    private Date transactiontime ;

    @ManyToOne
    @JsonSerialize(using = UserSerializer.class)
    private User creator;

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

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public ParticipantEvent getParticipantEvent() {
        return participantEvent;
    }

    public void setParticipantEvent(ParticipantEvent participantEvent) {
        this.participantEvent = participantEvent;
    }


}
