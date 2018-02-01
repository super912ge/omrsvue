package com.proship.omrs.candidate.candidate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;


@Entity
public class ParticipantEvent {

    @Id
    private Long id;



    @OneToOne(mappedBy = "comment")
    public ParticipantEventCommentTts participantEventCommentTts;

    @OneToOne(mappedBy = "participantEvent")
    @JsonIgnore
    private ParticipantEventTypeTts participantEventTypeTts;

    @Transient
    private ParticipantEventType participantEventType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ParticipantEventCommentTts getParticipantEventCommentTts() {
        return participantEventCommentTts;
    }

    public void setParticipantEventCommentTts(ParticipantEventCommentTts participantEventCommentTts) {
        this.participantEventCommentTts = participantEventCommentTts;
    }


    public ParticipantEventTypeTts getParticipantEventTypeTts() {
        return participantEventTypeTts;
    }

    public void setParticipantEventTypeTts(ParticipantEventTypeTts participantEventTypeTts) {
        this.participantEventTypeTts = participantEventTypeTts;
    }

    public ParticipantEventType getParticipantEventType() {

        if(this.participantEventTypeTts!=null) this.participantEventType = participantEventTypeTts.getParticipantEventType();
        return participantEventType;
    }

    public void setParticipantEventType(ParticipantEventType participantEventType) {
        this.participantEventType = participantEventType;
    }
}
