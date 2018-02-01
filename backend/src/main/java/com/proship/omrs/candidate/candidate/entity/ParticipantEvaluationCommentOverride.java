package com.proship.omrs.candidate.candidate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@Where(clause = "nexttransactiontime > current_date")
public class ParticipantEvaluationCommentOverride {


    @Id
    private Long id ;

    @JsonIgnore
    private Date nexttransactiontime ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Participant participant;


    @ManyToOne
    @JoinColumn(name = "id",insertable = false,updatable = false)
    private ParticipantEvaluationCommentTemporal participantEvaluationCommentTemporal;

}
