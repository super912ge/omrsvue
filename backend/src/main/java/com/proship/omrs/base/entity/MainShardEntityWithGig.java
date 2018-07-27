package com.proship.omrs.base.entity;

import com.proship.omrs.gig.entity.Gig;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
public class MainShardEntityWithGig extends MainShardEntity {


    @ManyToOne
    @JoinColumn(name="gig_id")
    private Gig gig;

    public Gig getGig() {
        return gig;
    }

    public void setGig(Gig gig) {
        this.gig = gig;
    }
}
