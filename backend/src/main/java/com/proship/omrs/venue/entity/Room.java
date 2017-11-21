package com.proship.omrs.venue.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proship.omrs.base.entity.BaseEntity;
import com.proship.omrs.client.entity.Client;
import com.proship.omrs.client.entity.ClientMap;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

@Entity
public class Room extends BaseEntity{

    @Id
    private Long id ;
    private String name;
    private String type ;
    private Long clientId ;
    private Long creatorId;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date validstarttime ;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date validendtime ;
    private Venue venue ;
    @Transient
    private Client client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Date getValidstarttime() {
        return validstarttime;
    }

    public void setValidstarttime(Date validstarttime) {
        this.validstarttime = validstarttime;
    }

    public Date getValidendtime() {
        return validendtime;
    }

    public void setValidendtime(Date validendtime) {
        this.validendtime = validendtime;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Client getClient() {
        return ClientMap.getClient(clientId);
    }

    public void setClient(Client client) {
        this.client = client;
        this.clientId = client.getId();
    }
}
