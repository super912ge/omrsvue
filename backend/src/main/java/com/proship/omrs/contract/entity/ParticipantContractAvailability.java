package com.proship.omrs.contract.entity;

import javax.persistence.Entity;
import java.sql.Timestamp;


public class ParticipantContractAvailability {

    Long id;

    Long participantId;

    Timestamp validtimestart;

    Timestamp validtimeend;

    Long contractIdPrev;

    Long contractIdNext;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public Timestamp getValidtimestart() {
        return validtimestart;
    }

    public void setValidtimestart(Timestamp validtimestart) {
        this.validtimestart = validtimestart;
    }

    public Timestamp getValidtimeend() {
        return validtimeend;
    }

    public void setValidtimeend(Timestamp validtimeend) {
        this.validtimeend = validtimeend;
    }

    public Long getContractIdPrev() {
        return contractIdPrev;
    }

    public void setContractIdPrev(Long contractIdPrev) {
        this.contractIdPrev = contractIdPrev;
    }

    public Long getContractIdNext() {
        return contractIdNext;
    }

    public void setContractIdNext(Long contractIdNext) {
        this.contractIdNext = contractIdNext;
    }
}
