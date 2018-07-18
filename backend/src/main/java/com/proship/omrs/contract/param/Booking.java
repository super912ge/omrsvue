package com.proship.omrs.contract.param;


import com.proship.omrs.contract.entity.Pay;

import java.sql.Timestamp;
import java.util.Date;

public class Booking {

    private Date startDate;

    private Date endDate;

    private Boolean nonPs;

    private Long participantActId;

    private Date eventDate;

    private Date formDate;

    private Date ASAAcceptDate;

    private Long recruiterId;

    private Long gigId;

    private Long rank;

    private Pay pay;

    public Boolean getNonPs() {
        return nonPs;
    }

    public void setNonPs(Boolean nonPs) {
        this.nonPs = nonPs;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getParticipantActId() {
        return participantActId;
    }

    public void setParticipantActId(Long participantActId) {
        this.participantActId = participantActId;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Date getFormDate() {
        return formDate;
    }

    public void setFormDate(Date formDate) {
        this.formDate = formDate;
    }

    public Date getASAAcceptDate() {
        return ASAAcceptDate;
    }

    public void setASAAcceptDate(Date ASAAcceptDate) {
        this.ASAAcceptDate = ASAAcceptDate;
    }

    public Long getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(Long recruiterId) {
        this.recruiterId = recruiterId;
    }

    public Long getGigId() {
        return gigId;
    }

    public void setGigId(Long gigId) {
        this.gigId = gigId;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public Pay getPay() {
        return pay;
    }

    public void setPay(Pay pay) {
        this.pay = pay;
    }
}
