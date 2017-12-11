package com.proship.omrs.candidate.candidate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Where(clause = "nexttransactiontime > current_date")
public class ParticipantCitizenshipOverride {

    @Id
    private Long id;

    @JsonIgnore
    private Long destroyerId;

    @JsonIgnore
    private Timestamp nexttransactiontime;

    @ManyToOne
    @JoinColumn(name = "id",insertable = false,updatable = false)
    private ParticipantCitizenship participantCitizenship;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Participant participant;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public ParticipantCitizenship getParticipantCitizenship() {
        return participantCitizenship;
    }

    public void setParticipantCitizenship(ParticipantCitizenship participantCitizenship) {
        this.participantCitizenship = participantCitizenship;
    }
}
