package com.proship.omrs.system.country.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Country {

    @Id
    private Long id;

    private String name;

    private String iso_alpha3;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIso_alpha3() {
        return iso_alpha3;
    }

    public void setIso_alpha3(String iso_alpha3) {
        this.iso_alpha3 = iso_alpha3;
    }
}
