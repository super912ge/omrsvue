package com.proship.omrs.candidate.availability.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AvailabilityLevel {

    @Id
    private Long id ;
    private String name;
    private Integer ordering ;

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

    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }
}
