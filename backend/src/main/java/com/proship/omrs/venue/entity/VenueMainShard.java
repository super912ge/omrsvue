package com.proship.omrs.venue.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.base.entity.MainShardEntity;
import com.proship.omrs.client.entity.Client;
import com.proship.omrs.client.entity.ClientMap;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Where(clause = "validendtime> current_date and nexttransactiontime > current_date")
public class VenueMainShard extends MainShardEntity{

    @Id
    private Long id;

    private String name;

    private String type;

    @Transient
    private String clientCode;
    private Long clientId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonIgnore
//    private Venue venue;


    private Long venueId;

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

    public void setClientId(Long client) {
        this.clientId = client;
    }

    public String getClientCode() {
        this.clientCode = ClientMap.getClient(clientId).getCode();
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public Long getVenueId() {

        return venueId;
    }

    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }
}
