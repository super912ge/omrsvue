package com.proship.omrs.candidate.group.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.proship.omrs.base.entity.MainShardEntity;
import com.proship.omrs.candidate.candidate.entity.Participant;
import com.proship.omrs.candidate.candidate.param.ParticipantSerializer;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

public class GroupActMainShard extends MainShardEntity {

    @Id
    private Long id;

    private String name ;

    @ManyToOne
    @JsonSerialize(using = ParticipantSerializer.class)
    private Participant leader;

    @OneToOne(mappedBy = "groupActMainShard")
    private Act act;

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

    public Participant getLeader() {
        return leader;
    }

    public void setLeader(Participant leader) {
        this.leader = leader;
    }

    public Act getAct() {
        return act;
    }

    public void setAct(Act act) {
        this.act = act;
    }
}
