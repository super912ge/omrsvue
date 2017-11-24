package com.proship.omrs.candidate.group.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.base.entity.MainShardEntity;
import com.proship.omrs.candidate.candidate.entity.ParticipantAct;

import javax.persistence.*;
import java.util.Set;

@Entity
public class GroupActMemberShard extends MainShardEntity{

    @Id
    private Long id;

    @ManyToOne
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
}
