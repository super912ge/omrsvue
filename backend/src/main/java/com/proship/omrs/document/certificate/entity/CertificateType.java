package com.proship.omrs.document.certificate.entity;

import com.proship.omrs.document.base.entity.DocumentType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "certificate_type")

public class CertificateType extends DocumentType{

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
