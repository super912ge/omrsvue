package com.proship.omrs.contract.param;

import java.util.Date;
import java.util.Set;

public class ContractSearchingParamIn {

    private String name;

    private Set<Long> clientIds;

    private Set<Long> venueIds;

    private Long gigTypeId;

    private String roomName;

    private Long rank;

    private Date startDate;

    private Date endDate;

    private Date seaDate;

    private Boolean seaDateOn;

    private Boolean exclusive;

    private Boolean nonPs;

    private Boolean canceled;

    private Long accountManager;

    private Long recruiter;

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

    public Boolean getSeaDateOn() {
        return seaDateOn;
    }

    public void setSeaDateOn(Boolean seaDateOn) {
        this.seaDateOn = seaDateOn;
    }

    public void setSeaDate(Date seaDate) {
        this.seaDate = seaDate;
    }

    public Boolean getExclusive() {
        return exclusive;
    }

    public void setExclusive(Boolean exclusive) {
        this.exclusive = exclusive;
    }

    public Boolean getNonPs() {
        return nonPs;
    }

    public void setNonPs(Boolean nonPs) {
        this.nonPs = nonPs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Long> getClientIds() {
        return clientIds;
    }

    public void setClientIds(Set<Long> clientIds) {
        this.clientIds = clientIds;
    }

    public Set<Long> getVenueIds() {
        return venueIds;
    }

    public void setVenueIds(Set<Long> venueIds) {
        this.venueIds = venueIds;
    }

    public Long getGigTypeId() {
        return gigTypeId;
    }

    public void setGigTypeId(Long gigTypeId) {
        this.gigTypeId = gigTypeId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public Boolean getCanceled() {
        return canceled;
    }

    public void setCanceled(Boolean canceled) {
        this.canceled = canceled;
    }

    public Long getAccountManager() {
        return accountManager;
    }

    public void setAccountManager(Long accountManager) {
        this.accountManager = accountManager;
    }

    public Long getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(Long recruiter) {
        this.recruiter = recruiter;
    }

    @Override
    public String toString() {
        return "ContractSearchingParamIn{" +
                "name='" + name + '\'' +
                ", clientIds=" + clientIds +
                ", venueIds=" + venueIds +
                ", gigTypeId=" + gigTypeId +
                ", roomName='" + roomName + '\'' +
                ", rank=" + rank +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", seaDate=" + seaDate +
                ", seaDateOn=" + seaDateOn +
                ", exclusive=" + exclusive +
                ", nonPs=" + nonPs +
                ", canceled=" + canceled +
                ", accountManager=" + accountManager +
                ", recruiter=" + recruiter +
                '}';
    }
}
