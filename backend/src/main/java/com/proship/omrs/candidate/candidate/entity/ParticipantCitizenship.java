package com.proship.omrs.candidate.candidate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.system.country.entity.Country;
import com.proship.omrs.system.country.entity.CountryMap;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.sql.Timestamp;

@Entity
public class ParticipantCitizenship {


    @Id
    private Long id;

    @JsonIgnore
    private Timestamp transactiontime;

    @JsonIgnore
    private Long creatorId;

    @JsonIgnore
    private Long countryId;

    @Transient
    private Country country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getTransactiontime() {
        return transactiontime;
    }

    public void setTransactiontime(Timestamp transactiontime) {
        this.transactiontime = transactiontime;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Country getCountry() {

        this.country = CountryMap.getCountry(countryId);
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
