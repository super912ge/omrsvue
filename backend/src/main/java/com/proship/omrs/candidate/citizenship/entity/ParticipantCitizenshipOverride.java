package com.proship.omrs.candidate.citizenship.entity;

import com.proship.omrs.candidate.base.entity.BaseOverrideEntity;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Where(clause = "nexttransactiontime > current_date")
public class ParticipantCitizenshipOverride extends BaseOverrideEntity{

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id",insertable = false,updatable = false)
    private ParticipantCitizenship participantCitizenship;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ParticipantCitizenship getParticipantCitizenship() {
        return participantCitizenship;
    }

    public void setParticipantCitizenship(ParticipantCitizenship participantCitizenship) {
        this.participantCitizenship = participantCitizenship;
    }
}
