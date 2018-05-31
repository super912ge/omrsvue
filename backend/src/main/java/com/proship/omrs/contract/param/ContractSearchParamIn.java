package com.proship.omrs.contract.param;

import com.proship.omrs.gig.param.GigSearchParam;

import java.util.Date;
import java.util.Set;

public class ContractSearchParamIn {

    private GigSearchParam gigSearchParam;

    private String name;

    private Long rank;

    private Date startDate;

    private Date endDate;

    private Date seaDate;

    private Boolean seaDateOn;

    private Boolean nonPs;

    private Boolean canceled;

    private Long recruiter;

    private Long pageNumber;

    public Long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public GigSearchParam getGigSearchParam() {
        return gigSearchParam;
    }

    public void setGigSearchParam(GigSearchParam gigSearchParam) {
        this.gigSearchParam = gigSearchParam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
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

    public Date getSeaDate() {
        return seaDate;
    }

    public void setSeaDate(Date seaDate) {
        this.seaDate = seaDate;
    }

    public Boolean getSeaDateOn() {
        return seaDateOn;
    }

    public void setSeaDateOn(Boolean seaDateOn) {
        this.seaDateOn = seaDateOn;
    }

    public Boolean getNonPs() {
        return nonPs;
    }

    public void setNonPs(Boolean nonPs) {
        this.nonPs = nonPs;
    }

    public Boolean getCanceled() {
        return canceled;
    }

    public void setCanceled(Boolean canceled) {
        this.canceled = canceled;
    }

    public Long getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(Long recruiter) {
        this.recruiter = recruiter;
    }
}
