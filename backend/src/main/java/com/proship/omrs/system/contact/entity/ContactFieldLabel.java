package com.proship.omrs.system.contact.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class ContactFieldLabel {


    @Id
    private Long id ;

    private String label ;
    private Integer ordering ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }
}
