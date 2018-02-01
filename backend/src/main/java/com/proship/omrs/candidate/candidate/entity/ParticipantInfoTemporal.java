package com.proship.omrs.candidate.candidate.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ParticipantInfoTemporal  {


    @Id
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date transactiontime ;

    private Long creatorId;

    private String participantinfotype ;
    private String participantinfovalue ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTransactiontime() {
        return transactiontime;
    }

    public void setTransactiontime(Date transactiontime) {
        this.transactiontime = transactiontime;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getParticipantinfotype() {
        return participantinfotype;
    }

    public void setParticipantinfotype(String participantinfotype) {
        this.participantinfotype = participantinfotype;
    }

    public String getParticipantinfovalue() {
        return participantinfovalue;
    }

    public void setParticipantinfovalue(String participantinfovalue) {
        this.participantinfovalue = participantinfovalue;
    }
}
