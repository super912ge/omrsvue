package com.proship.omrs.venue.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Room {

    @Id
    private Long id;

    private String name;

   @JoinColumn(name = "venue")
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

    public VenueBase getVenue() {
        return venue;
    }

    public void setVenue(VenueBase venue) {
        this.venue = venue;
    }
}
