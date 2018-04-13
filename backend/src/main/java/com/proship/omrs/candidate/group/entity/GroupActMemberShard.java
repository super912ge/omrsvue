package com.proship.omrs.candidate.group.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.base.entity.MainShardEntity;
import com.proship.omrs.candidate.participant.entity.ParticipantAct;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Entity
@Where(clause = "nexttransactiontime>now()")
public class GroupActMemberShard extends MainShardEntity{

    @Id
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Act act ;

    @ManyToMany
    @JoinTable(
                    name = "Group_Act_Members",
                    joinColumns = {@JoinColumn(name="member_shard_id")},
                    inverseJoinColumns = {@JoinColumn(name="act_id")}
            )

    @JsonIgnore
    Set<ParticipantAct> participantActs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Act getAct() {
        return act;
    }

    public void setAct(Act act) {
        this.act = act;
    }

    public Set<ParticipantAct> getParticipantActs() {
        return participantActs;
    }

    public void setParticipantActs(Set<ParticipantAct> participantActs) {
        this.participantActs = participantActs;
    }
}
