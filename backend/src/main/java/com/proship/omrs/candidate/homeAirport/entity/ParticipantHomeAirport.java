package com.proship.omrs.candidate.homeAirport.entity;

import com.proship.omrs.candidate.base.entity.BaseEntity;
import com.proship.omrs.system.airport.entity.Airport;

import javax.persistence.*;

@Entity
public class ParticipantHomeAirport extends BaseEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="participant_home_airport_id_seq")
    @SequenceGenerator(
            name="participant_home_airport_id_seq",
            sequenceName="participant_home_airport_id_sequence"
    )
    private Long id;

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
