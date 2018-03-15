package com.proship.omrs.contract.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReasonCodeMetaType {

    @Id
    private Long id;

    private String label;
    private String description ;
    private Boolean invoiceable ;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getInvoiceable() {
        return invoiceable;
    }

    public void setInvoiceable(Boolean invoiceable) {
        this.invoiceable = invoiceable;
    }
}
