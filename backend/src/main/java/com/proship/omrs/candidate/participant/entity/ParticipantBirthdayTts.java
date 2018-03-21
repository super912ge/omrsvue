package com.proship.omrs.candidate.participant.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.base.entity.BaseEntity;
import com.proship.omrs.base.entity.BaseEntityWithCreator;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@Where(clause = "nexttransactiontime>current_date")
public class ParticipantBirthdayTts extends BaseEntityWithCreator{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "participant_birthday_tts_id_seq")
    @SequenceGenerator(
            name = "participant_birthday_tts_id_seq",
            sequenceName = "participant_birthday_tts_id_sequence"
    )
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Participant participant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getValue() {
        return value;
    }

    public void setValue(Date value) {
        this.value = value;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }
}
