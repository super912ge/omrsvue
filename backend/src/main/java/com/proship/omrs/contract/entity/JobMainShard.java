package com.proship.omrs.contract.entity;

import com.proship.omrs.candidate.group.entity.Act;
import com.proship.omrs.base.entity.MainShardEntity;
import com.proship.omrs.gig.entity.Gig;

import javax.persistence.*;

@Entity
public class JobMainShard extends MainShardEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="job_main_shard_id_seq")
    @SequenceGenerator(
            name="job_main_shard_id_seq",
            sequenceName="job_main_shard_id_sequence"
    )
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
