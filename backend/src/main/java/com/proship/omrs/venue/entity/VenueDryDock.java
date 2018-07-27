package com.proship.omrs.venue.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.base.entity.MainShardEntity;
import org.hibernate.annotations.Where;

import javax.persistence.*;


@Entity
@Where(clause = "nexttransactiontime>now()")
public class VenueDryDock extends MainShardEntity{


    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="venue_dry_dock_id_seq")
    @SequenceGenerator(
            name="venue_dry_dock_id_seq",
            sequenceName="venue_dry_dock_id_sequence"
    )
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private VenueBase venue;

    public VenueBase getVenue() {
        return venue;
    }

    public void setVenue(VenueBase venue) {
        this.venue = venue;
    }
}
