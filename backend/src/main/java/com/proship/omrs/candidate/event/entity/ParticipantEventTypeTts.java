package com.proship.omrs.candidate.event.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@Where(clause = "nexttransactiontime > now()")
public class ParticipantEventTypeTts {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="participant_event_type_tts_id_seq")
    @SequenceGenerator(
            name="participant_event_type_tts_id_seq",
            sequenceName="participant_event_type_tts_id_sequence"
    )
    @JsonIgnore
    private Long id;


    private Long participantEventTypeId;

    @JsonIgnore
    private Long creatorId;

    @JsonIgnore
    private Date transactiontime ;

    @JsonIgnore
    private Date nexttransactiontime ;

    @JsonIgnore
    @ManyToOne
    private ParticipantEvent participantEvent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParticipantEventTypeId() {
        return participantEventTypeId;
    }

    public void setParticipantEventTypeId(Long participantEventTypeId) {
        this.participantEventTypeId = participantEventTypeId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Date getTransactiontime() {
        return transactiontime;
    }

    public void setTransactiontime(Date transactiontime) {
        this.transactiontime = transactiontime;
    }

    public Date getNexttransactiontime() {
        return nexttransactiontime;
    }

    public void setNexttransactiontime(Date nexttransactiontime) {
        this.nexttransactiontime = nexttransactiontime;
    }

    public ParticipantEvent getParticipantEvent() {
        return participantEvent;
    }

    public void setParticipantEvent(ParticipantEvent participantEvent) {
        this.participantEvent = participantEvent;
    }
}
