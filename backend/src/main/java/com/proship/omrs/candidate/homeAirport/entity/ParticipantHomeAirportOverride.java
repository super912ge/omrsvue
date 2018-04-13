package com.proship.omrs.candidate.homeAirport.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.candidate.base.entity.BaseOverrideEntity;
import com.proship.omrs.candidate.participant.entity.Participant;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Where(clause = "nexttransactiontime> now()")
public class ParticipantHomeAirportOverride extends BaseOverrideEntity {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id",insertable = false,updatable = false)
    ParticipantHomeAirport participantHomeAirport;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public ParticipantHomeAirport getParticipantHomeAirport() {
        return participantHomeAirport;
    }

    public void setParticipantHomeAirport(ParticipantHomeAirport participantHomeAirport) {
        this.participantHomeAirport = participantHomeAirport;
    }
}
