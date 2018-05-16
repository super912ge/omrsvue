package com.proship.omrs.contract.param;

import java.util.Set;

public class GigSearchParamIn extends ContractSearchingParamIn{

    private Set<Long> clientIds;

    private Set<Long> venueIds;

    private Long gigTypeId;

    private String roomName;

    private Long rank;

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
}
