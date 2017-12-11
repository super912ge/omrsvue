package com.proship.omrs.candidate.candidate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.base.entity.MainShardEntity;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
@Where(clause = "nexttransactiontime > current_date and validendtime>current_date")
public class ParticipantAvailabilityBts extends MainShardEntity{

    @Id
    private Long id ;

    @JsonIgnore
    private Long level;

    @Transient
    private AvailabilityLevel interestLevel;

    @ManyToOne
    @JsonIgnore
    private Participant participant;

    public AvailabilityLevel getInterestLevel() {
        return interestLevel;
    }

    public void setInterestLevel(AvailabilityLevel interestLevel) {
        this.interestLevel = interestLevel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }
}
