package com.proship.omrs.gig.entity;

import com.proship.omrs.base.entity.MainShardEntity;
import org.hibernate.annotations.Where;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Where(clause = "nexttransactiontime > current_date and validendtime>current_date")
public class GigTerritoryShard extends MainShardEntity{

    @Id
    private Long id;

    @OneToOne
    private Gig gig ;
}
