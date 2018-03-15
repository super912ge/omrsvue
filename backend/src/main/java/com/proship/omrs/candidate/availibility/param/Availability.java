package com.proship.omrs.candidate.availibility.param;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Availability {

    private Date startDate;

    private Date endDate;

    private Integer interestLevel;

    private String comment;

    private Long contractId;

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

    public Integer getInterestLevel() {
        return interestLevel;
    }

    public void setInterestLevel(Integer interestLevel) {
        this.interestLevel = interestLevel;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }
}
