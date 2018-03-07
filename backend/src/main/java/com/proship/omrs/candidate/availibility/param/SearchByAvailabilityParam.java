package com.proship.omrs.candidate.availibility.param;

import java.util.Date;

public class SearchByAvailabilityParam {

    private Date startDate;

    private Date endDate;

    private Integer interestLevel;

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
}
