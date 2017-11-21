package com.proship.omrs.gig.entity;

import com.proship.omrs.base.entity.BaseEntity;
import sun.rmi.runtime.Log;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class GigTerritoryShard extends BaseEntity{

    @Id
    private Long id;

    @OneToOne
    private Gig gig ;
}
