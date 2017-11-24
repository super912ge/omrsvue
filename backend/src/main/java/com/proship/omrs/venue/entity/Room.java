package com.proship.omrs.venue.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proship.omrs.base.entity.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

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
