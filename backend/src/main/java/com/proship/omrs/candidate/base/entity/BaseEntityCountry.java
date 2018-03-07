package com.proship.omrs.candidate.base.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.system.country.entity.Country;
import com.proship.omrs.system.country.entity.CountryMap;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public class BaseEntityCountry extends BaseEntity{


    @JsonIgnore
    private Long countryId;

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    @Transient
    private Country country;

    public Country getCountry() {

        this.country = CountryMap.getCountry(getCountryId());
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
