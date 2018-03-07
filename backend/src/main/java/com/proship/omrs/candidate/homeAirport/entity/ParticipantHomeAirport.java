package com.proship.omrs.candidate.homeAirport.entity;

import com.proship.omrs.candidate.base.entity.BaseEntity;
import com.proship.omrs.system.airport.entity.Airport;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class ParticipantHomeAirport extends BaseEntity{


    @Id
    private Long id;

    private Long creatorId;

    @ManyToOne
    @JoinColumn(name = "airportId")
    private Airport airport;

    private String comment ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
