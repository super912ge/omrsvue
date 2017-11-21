package com.proship.omrs.contract.entity;

import com.proship.omrs.candidate.group.entity.Act;
import com.proship.omrs.base.entity.MainShardEntity;
import com.proship.omrs.gig.entity.Gig;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class JobMainShard extends MainShardEntity{

    private Long id;

    @ManyToOne
    @JoinColumn(name = "gigId")
    private Gig gig;

    @ManyToOne
    private Job job;

    @ManyToOne
    private Act groupAct;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Gig getGig() {
        return gig;
    }

    public void setGig(Gig gig) {
        this.gig = gig;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Act getGroupAct() {
        return groupAct;
    }

    public void setGroupAct(Act groupAct) {
        this.groupAct = groupAct;
    }
}
