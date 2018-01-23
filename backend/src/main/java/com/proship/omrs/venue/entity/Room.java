package com.proship.omrs.venue.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Room {

    @Id
    private Long id;

    private String name;

    private Long venue;

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

    public Long getVenue() {
        return venue;
    }

    public void setVenue(Long venue) {
        this.venue = venue;
    }
}
