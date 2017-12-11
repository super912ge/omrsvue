package com.proship.omrs.gig.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Position {

    @Id
    private Long id;

    private String name;

    private Integer ordering;

    private Integer grouping ;

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

    public Integer getGrouping() {
        return grouping;
    }

    public void setGrouping(Integer grouping) {
        this.grouping = grouping;
    }
}
