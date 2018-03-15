package com.proship.omrs.candidate.contact.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.candidate.base.entity.BaseOverrideEntity;
import com.proship.omrs.candidate.participant.entity.Participant;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Where(clause = "nexttransactiontime > current_date")
public class ParticipantContactFieldOverride extends BaseOverrideEntity{

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private ParticipantContactField participantContactField;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Participant participant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ParticipantContactField getParticipantContactField() {
        return participantContactField;
    }

    public void setParticipantContactField(ParticipantContactField participantContactField) {
        this.participantContactField = participantContactField;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }
}


