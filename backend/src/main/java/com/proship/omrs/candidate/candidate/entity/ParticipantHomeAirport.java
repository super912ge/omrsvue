package com.proship.omrs.candidate.candidate.entity;

import com.proship.omrs.system.airport.entity.Airport;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class ParticipantHomeAirport {


    @Id
    private Long id;

    private Date transactiontime ;

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

    public Date getTransactiontime() {
        return transactiontime;
    }

    public void setTransactiontime(Date transactiontime) {
        this.transactiontime = transactiontime;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
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
