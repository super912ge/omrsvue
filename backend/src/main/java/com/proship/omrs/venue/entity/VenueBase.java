package com.proship.omrs.venue.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "venue")
public class VenueBase {

    @Id
    private Long id;

    @JsonIgnore
    private Integer specialtyType;

    @Transient
    private VenueMainShard venueMainShard;

    @OneToMany(mappedBy = "venue", fetch = FetchType.LAZY)
    @OrderBy("validendtime desc")
    @JsonIgnore
    private List<VenueMainShard> venueMainShards;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "venue")
    private Set<Room> rooms;

    @OneToMany
    private List<VenueDryDock> dryDocks;

    @OneToOne(mappedBy = "venue",fetch = FetchType.LAZY)
    private VenuePeriodShard venuePeriodShard;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSpecialtyType() {
        return specialtyType;
    }

    public void setSpecialtyType(Integer specialtyType) {
        this.specialtyType = specialtyType;
    }

    public VenueMainShard getVenueMainShard() {

        if (venueMainShard==null) {
            List<VenueMainShard> shards = venueMainShards.stream().filter(m -> m.getValidendtime()
                    .after(new Date())).collect(Collectors.toList());

            if (!shards.isEmpty())
            setVenueMainShard(shards.get(0));
        }
        return venueMainShard;
    }

    public void setVenueMainShard(VenueMainShard venueMainShard) {
        this.venueMainShard = venueMainShard;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public VenuePeriodShard getVenuePeriodShard() {
        return venuePeriodShard;
    }

    public void setVenuePeriodShard(VenuePeriodShard venuePeriodShard) {
        this.venuePeriodShard = venuePeriodShard;
    }

    public List<VenueMainShard> getVenueMainShards() {
        return venueMainShards;
    }

    public void setVenueMainShards(List<VenueMainShard> venueMainShards) {
        this.venueMainShards = venueMainShards;
    }

    public List<VenueDryDock> getDryDocks() {
        return dryDocks;
    }

    public void setDryDocks(List<VenueDryDock> dryDocks) {
        this.dryDocks = dryDocks;
    }
}