package com.proship.omrs.venue.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.base.entity.MainShardEntity;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Where(clause = "validendtime > now() and nexttransactiontime> now()")
public class VenuePeriodShard extends MainShardEntity {

    @Id
    private Long id;

    private Integer startConfidence ;

    private Integer endConfidence;

    @ManyToOne
    @JsonIgnore
    private Venue venue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStartConfidence() {
        return startConfidence;
    }

    public void setStartConfidence(Integer startConfidence) {
        this.startConfidence = startConfidence;
    }

    public Integer getEndConfidence() {
        return endConfidence;
    }

    public void setEndConfidence(Integer endConfidence) {
        this.endConfidence = endConfidence;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }
}
