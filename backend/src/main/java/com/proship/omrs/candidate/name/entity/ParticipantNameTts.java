package com.proship.omrs.candidate.name.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.base.entity.BaseEntity;
import com.proship.omrs.candidate.participant.entity.Participant;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Where(clause = "nexttransactiontime > current_date")
public class ParticipantNameTts extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="participant_name_tts_id_seq")
    @SequenceGenerator(name="participant_name_tts_id_seq",
            sequenceName="participant_name_tts_id_sequence")
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Participant participant;

    private String firstName;
    private String lastName;
    private String otherName;

    private Long creatorId;

    private String pronunciation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
}
