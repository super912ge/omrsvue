package com.proship.omrs.client.entity;

import com.proship.omrs.document.seamanBook.entity.SeamansBook;
import com.proship.omrs.venue.entity.Venue;
import com.proship.omrs.venue.entity.VenueMainShard;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Entity
public class Client {

    @Id
    private Long id  ;
    private Long uuid  ;
    private String name  ;
    private String code ;
    private Boolean active ;

    @OneToMany(mappedBy = "client")
    @Where(clause = "venues.validendtime > current_date and venues.nexttransactiontime> current_date")
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
