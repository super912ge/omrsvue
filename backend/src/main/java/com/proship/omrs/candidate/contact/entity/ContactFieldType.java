package com.proship.omrs.candidate.contact.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ContactFieldType {

   @Id
   private Long id ;

    private String label ;
    private Integer ordering ;
    private String validationregex ;

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

    public String getValidationregex() {
        return validationregex;
    }

    public void setValidationregex(String validationregex) {
        this.validationregex = validationregex;
    }
}
