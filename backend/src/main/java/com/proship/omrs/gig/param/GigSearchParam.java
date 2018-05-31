package com.proship.omrs.gig.param;

import java.util.List;
import java.util.Set;

public class GigSearchParam {

    private Long id;

    private String name;

    private Long gigTypeId;

    private List<Long> requirement;

    private Long accountManager;

    private Set<Long> clientIds;

    private Set<Long> venueIds;

    private String roomName;

    private Boolean exclusive;

    private Boolean group;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGigTypeId() {
        return gigTypeId;
    }

    public void setGigTypeId(Long gigTypeId) {
        this.gigTypeId = gigTypeId;
    }

    public List<Long> getRequirement() {
        return requirement;
    }

    public void setRequirement(List<Long> requirement) {
        this.requirement = requirement;
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

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Boolean getExclusive() {
        return exclusive;
    }

    public void setExclusive(Boolean exclusive) {
        this.exclusive = exclusive;
    }

    public Long getAccountManager() {
        return accountManager;
    }

    public void setAccountManager(Long accountManager) {
        this.accountManager = accountManager;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getGroup() {
        return group;
    }

    public void setGroup(Boolean group) {
        this.group = group;
    }
}
