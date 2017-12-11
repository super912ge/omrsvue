package com.proship.omrs.document.visa.entity;

import com.proship.omrs.document.base.entity.DocumentType;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class VisaType extends DocumentType{

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
