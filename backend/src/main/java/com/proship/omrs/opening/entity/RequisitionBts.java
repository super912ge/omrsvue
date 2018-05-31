package com.proship.omrs.opening.entity;

import com.proship.omrs.base.entity.BaseEntityWithCreator;
import com.proship.omrs.base.entity.MainShardEntity;
import com.proship.omrs.base.entity.MainShardEntityWithGig;
import com.proship.omrs.gig.entity.Gig;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Where(clause = "nexttransactiontime > now()")
public class RequisitionBts extends MainShardEntityWithGig {

    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name="gig_id")
    private Gig gig;


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
