package com.proship.omrs.candidate.residency.entity;

import com.proship.omrs.candidate.base.entity.BaseOverrideEntity;
import org.hibernate.annotations.Where;
import javax.persistence.*;

@Entity
@Where(clause = "nexttransactiontime > now()")
public class ParticipantResidencyOverride extends BaseOverrideEntity{

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id",insertable = false,updatable = false)
    private ParticipantResidency participantResidency;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ParticipantResidency getParticipantResidency() {
        return participantResidency;
    }

    public void setParticipantResidency(ParticipantResidency participantResidency) {
        this.participantResidency = participantResidency;
    }
}
