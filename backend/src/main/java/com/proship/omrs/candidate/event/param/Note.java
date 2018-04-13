package com.proship.omrs.candidate.event.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proship.omrs.candidate.event.entity.ParticipantEventOverride;

import java.util.Date;

public class Note {

    public Note (ParticipantEventOverride participantEventOverride){

        this.id = participantEventOverride.getId();
        this.type = participantEventOverride.getParticipantEventTemporal().
                getParticipantEvent().getParticipantEventTypeTts().getParticipantEventTypeId();

        this.text = participantEventOverride.getParticipantEventTemporal().getParticipantEvent()
                .getParticipantEventCommentTts().getText();
        this.transactionTime = participantEventOverride.getParticipantEventTemporal().getTransactiontime();
        this.username = participantEventOverride.getParticipantEventTemporal().getCreator().getShortName();
    }

    public Long id;

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    private Long type;

    private String text;

    private String username;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date transactionTime;

}
