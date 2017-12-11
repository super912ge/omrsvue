package com.proship.omrs.candidate.candidate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.candidate.group.entity.GroupActMemberShard;
import com.proship.omrs.contract.entity.ContractMainShard;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Where(clause = "active = true and id not in (1,2,2576,2577)")
public class ParticipantAct {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "participant_act_id_seq")
    @SequenceGenerator(
            name = "participant_act_id_seq",
            sequenceName = "participant_act_id_sequence"
    )
    private Long id;

    @JsonIgnore
    private Long specialtyType;
    @JsonIgnore
    private Long uuid;

    private Boolean active;

    @OneToMany(mappedBy = "act",fetch = FetchType.LAZY)
    private List<ContractMainShard> contractShards;

    @ManyToOne
    @JsonIgnore
    private Participant participant;

    @OneToOne(mappedBy = "participantAct")
    @JsonIgnore
    private ParticipantActName participantActName;

//    @OneToMany
//    ParticipantActResidency

    @ManyToMany(mappedBy = "participantActs",fetch = FetchType.LAZY)
    private Set<GroupActMemberShard> bands;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSpecialtyType() {
        return specialtyType;
    }

    public void setSpecialtyType(Long specialtyType) {
        this.specialtyType = specialtyType;
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

    public List<ContractMainShard> getContractShards() {
        return contractShards;
    }

    public void setContractShards(List<ContractMainShard> contractShards) {
        this.contractShards = contractShards;
    }

    public Set<GroupActMemberShard> getBands() {
        return bands;
    }

    public void setBands(Set<GroupActMemberShard> bands) {
        this.bands = bands;
    }
}
