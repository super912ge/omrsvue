package com.proship.omrs.document.medical.entity;

import com.proship.omrs.document.base.entity.DocumentType;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MedicalType extends DocumentType{

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }




}
