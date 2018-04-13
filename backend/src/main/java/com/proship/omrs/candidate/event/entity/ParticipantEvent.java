package com.proship.omrs.candidate.event.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;
import javax.persistence.*;


@Entity
public class ParticipantEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="participant_event_id_seq")
    @SequenceGenerator(
            name="participant_event_id_seq",
            sequenceName="participant_event_id_sequence"
    )
    private Long id;

    @OneToOne(mappedBy = "comment", fetch = FetchType.LAZY)
    @Where(clause = "nexttransactiontime > now()")
    @Fetch(FetchMode.SELECT)
    private ParticipantEventCommentTts participantEventCommentTts;

    @OneToOne(mappedBy = "participantEvent",fetch = FetchType.LAZY)
    @JsonIgnore
    @Fetch(FetchMode.SELECT)
    @Where(clause = "nexttransactiontime > now()")
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

        if(this.participantEventTypeTts!=null)
            this.participantEventType = ParticipantEventTypeMap.getParticipantEventType(
                    participantEventTypeTts.getParticipantEventTypeId());
        return participantEventType;
    }

    public void setParticipantEventType(ParticipantEventType participantEventType) {
        this.participantEventType = participantEventType;
    }
}
