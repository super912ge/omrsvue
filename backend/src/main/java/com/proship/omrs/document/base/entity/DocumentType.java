package com.proship.omrs.document.base.entity;


import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class DocumentType {

    private String name;

    private Long ordering;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOrdering() {
        return ordering;
    }

    public void setOrdering(Long ordering) {
        this.ordering = ordering;
    }
}
