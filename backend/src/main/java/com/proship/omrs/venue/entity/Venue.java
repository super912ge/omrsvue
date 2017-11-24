package com.proship.omrs.venue.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Venue {

    @Id
    private Long id;
    
    private Integer specialtyType;

    @OneToOne(mappedBy = "venue")
    private VenueMainShard venueMainShard;

    @OneToMany
    @JoinColumn(name = "venue")
    private Set<Room> rooms;

    @OneToOne(mappedBy = "venue")
    private VenuePeriodShard venuePeriodShard;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSpecialtyType() {
        return specialtyType;
    }

    public void setSpecialtyType(Integer specialtyType) {
        this.specialtyType = specialtyType;
    }

    public VenueMainShard getVenueMainShard() {
        return venueMainShard;
    }

    public void setVenueMainShard(VenueMainShard venueMainShard) {
        this.venueMainShard = venueMainShard;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public VenuePeriodShard getVenuePeriodShard() {
        return venuePeriodShard;
    }

    public void setVenuePeriodShard(VenuePeriodShard venuePeriodShard) {
        this.venuePeriodShard = venuePeriodShard;
    }
}