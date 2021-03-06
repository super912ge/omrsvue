package com.proship.omrs.msd.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MsdBracketSettingType {

    @Id
    private Long id;

    private String label;

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
}
