package com.proship.omrs.client.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.proship.omrs.venue.entity.VenueMainShard;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Client {

    @Id
    private Long id  ;

    @JsonIgnore
    private Long uuid  ;

    private String name ;

    private String code ;

    private Boolean active ;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private Set<VenueMainShard> venues;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<VenueMainShard> getVenues() {
        return venues;
    }

    public void setVenues(Set<VenueMainShard> venues) {
        this.venues = venues;
    }
}
