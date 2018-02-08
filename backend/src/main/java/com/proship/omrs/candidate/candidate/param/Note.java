package com.proship.omrs.candidate.candidate.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proship.omrs.candidate.candidate.entity.ParticipantEventOverride;

import java.util.Date;

public class Note {

    public Note (ParticipantEventOverride participantEventOverride){
        this.type = participantEventOverride.getParticipantEventTemporal().getParticipantEvent()
                .getParticipantEventType().getId();

        this.text = participantEventOverride.getParticipantEventTemporal().getParticipantEvent()
                .getParticipantEventCommentTts().getText();
        this.transactionTime = participantEventOverride.getParticipantEventTemporal().getTransactiontime();
        this.username = participantEventOverride.getParticipantEventTemporal().getCreator().getShortName();
    }

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
