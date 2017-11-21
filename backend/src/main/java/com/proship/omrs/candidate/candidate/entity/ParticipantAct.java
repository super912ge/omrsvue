package com.proship.omrs.candidate.candidate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.candidate.group.entity.GroupActMembers;
import com.proship.omrs.contract.entity.ContractMainShard;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
public class ParticipantAct {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "participant_act_id_seq")
    @SequenceGenerator(
            name = "participant_act_id_seq",
            sequenceName = "participant_act_id_sequence"
    )
    private Long id;
    private Long specialty_type;
    private Long uuid;
    private Boolean active;

    @OneToMany
    private List<ContractMainShard> contractShards;

    @ManyToOne
    @JsonIgnore
    private Participant participant;

    @OneToOne(mappedBy = "participantAct")
    @Where(clause = "participantActName.nexttransactiontime > current_date")
    private ParticipantActName participantActName;

//    @OneToMany
//    ParticipantActResidency

    @OneToOne(mappedBy = "participantAct")
    private GroupActMembers group;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSpecialty_type() {
        return specialty_type;
    }

    public void setSpecialty_type(Long specialty_type) {
        this.specialty_type = specialty_type;
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public ParticipantActName getParticipantActName() {
        return participantActName;
    }

    public void setParticipantActName(ParticipantActName participantActName) {
        this.participantActName = participantActName;
    }
}
