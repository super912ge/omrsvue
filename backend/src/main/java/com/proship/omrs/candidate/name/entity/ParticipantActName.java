package com.proship.omrs.candidate.name.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.base.entity.BaseEntity;
import com.proship.omrs.candidate.participant.entity.ParticipantAct;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "participant_act_name_tts")
@Where(clause = "nexttransactiontime > now()")
public class ParticipantActName extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="participant_act_name_tts_id_seq")
    @SequenceGenerator(
            name="participant_act_name_tts_id_seq",
            sequenceName="participant_act_name_tts_id_sequence"
    )
    private Long id;

    private String name;

    private Long creatorId;

    @ManyToOne
    @JsonIgnore
    private ParticipantAct participantAct;

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

    public ParticipantAct getParticipantAct() {
        return participantAct;
    }

    public void setParticipantAct(ParticipantAct participantAct) {
        this.participantAct = participantAct;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
}
