package com.proship.omrs.candidate.event.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.candidate.participant.entity.Participant;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@Where(clause = "nexttransactiontime > current_date")
public class ParticipantEventOverride {

    @Id
    private Long id ;

    @JsonIgnore
    private Date nexttransactiontime ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Participant participant;


    @ManyToOne
    @JoinColumn(name = "id",insertable = false,updatable = false)
    private ParticipantEventTemporal participantEventTemporal;



    public ParticipantEventTemporal getParticipantEventTemporal() {
        return participantEventTemporal;
    }

    public void setParticipantEventTemporal(ParticipantEventTemporal participantEventTemporal) {
        this.participantEventTemporal = participantEventTemporal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getNexttransactiontime() {
        return nexttransactiontime;
    }

    public void setNexttransactiontime(Date nexttransactiontime) {
        this.nexttransactiontime = nexttransactiontime;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }
}
