package com.proship.omrs.opening.param;

import com.proship.omrs.gig.param.GigSearchParam;

import java.util.Date;

public class OpeningSearchParam {

    private Date validStartDate;

    private Date validEndDate;

    private GigSearchParam gigSearchParam;

    private Boolean gap;

    public GigSearchParam getGigSearchParam() {
        return gigSearchParam;
    }

    public void setGigSearchParam(GigSearchParam gigSearchParam) {
        this.gigSearchParam = gigSearchParam;
    }

    public Date getValidStartDate() {
        return validStartDate;
    }

    public void setValidStartDate(Date validStartDate) {
        this.validStartDate = validStartDate;
    }

    public Date getValidEndDate() {
        return validEndDate;
    }

    public void setValidEndDate(Date validEndDate) {
        this.validEndDate = validEndDate;
    }

    public Boolean getGap() {
        return gap;
    }

    public void setGap(Boolean gap) {
        this.gap = gap;
    }

}
