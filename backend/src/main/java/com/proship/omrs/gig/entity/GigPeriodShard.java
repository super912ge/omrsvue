package com.proship.omrs.gig.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.base.entity.BaseEntity;
import com.proship.omrs.base.entity.MainShardEntity;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Where(clause = "nexttransactiontime > now() and validendtime>now()")
public class GigPeriodShard extends MainShardEntity{
    @Id
    private Long id;

    @JsonIgnore
    @OneToOne
    private Gig gig ;

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
}
