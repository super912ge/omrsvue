package com.proship.omrs.candidate.info.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.candidate.base.entity.BaseOverrideEntity;
import com.proship.omrs.candidate.participant.entity.Participant;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Where(clause = "nexttransactiontime > current_date")
public class ParticipantInfoOverride  extends BaseOverrideEntity{

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id",insertable = false,updatable = false)
    private ParticipantInfoTemporal participantInfoTemporal;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ParticipantInfoTemporal getParticipantInfoTemporal() {
        return participantInfoTemporal;
    }

    public void setParticipantInfoTemporal(ParticipantInfoTemporal participantInfoTemporal) {

        this.participantInfoTemporal = participantInfoTemporal;
    }
}
