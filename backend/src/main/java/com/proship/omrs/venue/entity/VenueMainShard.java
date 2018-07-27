package com.proship.omrs.venue.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.base.entity.MainShardEntity;
import com.proship.omrs.client.entity.Client;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Where(clause = " nexttransactiontime > now()")
public class VenueMainShard extends MainShardEntity{

    @Id
    private Long id;

    private String name;

    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Client client;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private VenueBase venue;

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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public VenueBase getVenue() {
        return venue;
    }

    public void setVenue(VenueBase venue) {
        this.venue = venue;
    }

    @Override
    public String toString() {
        return "VenueMainShard{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", clientCode='" + client.getCode() + '\'' +
                ", clientId=" + client.getId() +
                ", venueId=" + venue.getId() +
                '}';
    }
}
