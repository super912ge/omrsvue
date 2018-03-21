package com.proship.omrs.document.base.entity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Document {

    private Long uuid;

    private String number;

    private Long participantId;

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }
}
