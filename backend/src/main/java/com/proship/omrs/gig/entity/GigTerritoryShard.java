package com.proship.omrs.gig.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.base.entity.MainShardEntity;
import com.proship.omrs.user.entity.User;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Where(clause = "nexttransactiontime > now() and validendtime>now()")
public class GigTerritoryShard extends MainShardEntity{

    @Id
    private Long id;

    @OneToOne
    @JsonIgnore
    private Gig gig ;

    @JsonIgnore
    @ManyToOne
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
