package com.proship.omrs.candidate.group.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.proship.omrs.base.entity.MainShardEntity;
import com.proship.omrs.candidate.candidate.entity.Participant;
import com.proship.omrs.candidate.candidate.param.ParticipantSerializer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Where(clause = "validendtime>current_date and nexttransactiontime > current_date")
public class GroupActMainShard extends MainShardEntity {

    @Id
    private Long id;

    private String name ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonSerialize(using = ParticipantSerializer.class)
    private Participant leader;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "act_id")
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
