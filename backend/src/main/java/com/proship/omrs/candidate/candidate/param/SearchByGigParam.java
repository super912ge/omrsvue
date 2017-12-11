package com.proship.omrs.candidate.candidate.param;

import java.util.Set;


public class SearchByGigParam {

    private Boolean nonPs;

    private Long rank;

    private Set<Long> venueIds;

    private Set<Long> clientIds;

    private Long gigTypeId;

    public Set<Long> getVenueIds() {
        return venueIds;
    }

    public void setVenueIds(Set<Long> venueIds) {
        this.venueIds = venueIds;
    }

    public Set<Long> getClientIds() {
        return clientIds;
    }

    public void setClientIds(Set<Long> clientIds) {
        this.clientIds = clientIds;
    }

    public Long getGigTypeId() {
        return gigTypeId;
    }

    public void setGigTypeId(Long gigTypeId) {
        this.gigTypeId = gigTypeId;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public Boolean getNonPs() {
        return nonPs;
    }

    public void setNonPs(Boolean nonPs) {
        this.nonPs = nonPs;
    }
}
