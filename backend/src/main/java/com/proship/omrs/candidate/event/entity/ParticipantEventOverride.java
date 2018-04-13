package com.proship.omrs.candidate.event.entity;


import com.proship.omrs.candidate.base.entity.BaseOverrideEntity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import javax.persistence.*;


@Entity
@Where(clause = "nexttransactiontime > now()")
public class ParticipantEventOverride extends BaseOverrideEntity{

    @Id
    private Long id ;

    @ManyToOne
    @Fetch(FetchMode.SELECT)
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

}
