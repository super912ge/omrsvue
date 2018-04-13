package com.proship.omrs.gig.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.base.entity.BaseEntity;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Where(clause = "nexttransactiontime> now()")
public class ChairLabelTts extends BaseEntity{

    @Id
    private Long id;

    private String label;

    @OneToOne
    @JoinColumn(name = "chairId")
    @JsonIgnore
    private Gig gig;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Gig getGig() {
        return gig;
    }

    public void setGig(Gig gig) {
        this.gig = gig;
    }
}
