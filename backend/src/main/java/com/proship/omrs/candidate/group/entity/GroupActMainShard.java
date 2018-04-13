package com.proship.omrs.candidate.group.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.proship.omrs.base.entity.MainShardEntity;
import com.proship.omrs.candidate.participant.entity.Participant;
import com.proship.omrs.candidate.participant.param.ParticipantSerializer;
import com.proship.omrs.gig.entity.BandType;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Where(clause = "validendtime > now() and nexttransactiontime > now()")
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

    @OneToOne
    @JoinTable(
            name = "group_act_band_types",
            joinColumns = {@JoinColumn(name="groupactMainShardId")},
            inverseJoinColumns = {@JoinColumn(name="bandtypeId")}
    )
    private BandType bandType;

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

    public BandType getBandType() {
        return bandType;
    }

    public void setBandType(BandType bandType) {
        this.bandType = bandType;
    }
}
