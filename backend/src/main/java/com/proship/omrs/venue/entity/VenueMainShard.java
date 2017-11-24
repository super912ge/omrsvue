package com.proship.omrs.venue.entity;

import com.proship.omrs.base.entity.MainShardEntity;
import com.proship.omrs.client.entity.Client;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Where(clause = "validendtime> current_date and nexttransactiontime > current_date")
public class VenueMainShard extends MainShardEntity{

    @Id
    private Long id;

    private String name;

    private String type;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Venue venue;

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

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }
}
