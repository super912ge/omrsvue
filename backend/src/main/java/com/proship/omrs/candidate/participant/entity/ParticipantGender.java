package com.proship.omrs.candidate.participant.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.base.entity.BaseEntity;
import com.proship.omrs.base.entity.BaseEntityWithCreator;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "participant_gender_tts")
@Where(clause = "nexttransactiontime> now()")
public class ParticipantGender extends BaseEntityWithCreator{


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="participant_gender_tts_id_seq")
    @SequenceGenerator(
            name="participant_gender_tts_id_seq",
            sequenceName="participant_gender_tts_id_sequence"
    )
    private Long id;

    private String value;

    @ManyToOne
    @JsonIgnore
    private Participant participant;


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
