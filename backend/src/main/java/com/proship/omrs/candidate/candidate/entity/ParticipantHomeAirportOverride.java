package com.proship.omrs.candidate.candidate.entity;

import com.proship.omrs.system.airport.entity.Airport;
import com.proship.omrs.user.entity.User;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Where(clause = "nexttransactiontime> current_date")
public class ParticipantHomeAirportOverride {


    @Id
    private Long id;
    private Long destroyer_id;

    private Date nexttransactiontime;


    @ManyToOne Participant participant;


    @ManyToOne
    @JoinColumn(name = "id",insertable = false,updatable = false)
    ParticipantHomeAirport participantHomeAirport;


}
