package com.proship.omrs.evaluation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EvalTagType {

    @Id private Long id;

    private Long discriminatorId;

    private String label;

    private Boolean isrequired;

    private Boolean ismultiselect;

    private Integer ordering;

    private Boolean issearchableby;

    @JsonIgnore
    private Boolean isusegreaterthanquery;

    @JsonIgnore
    private Boolean issingleton;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDiscriminatorId() {
        return discriminatorId;
    }

    public void setDiscriminatorId(Long discriminatorId) {
        this.discriminatorId = discriminatorId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getIsrequired() {
        return isrequired;
    }

    public void setIsrequired(Boolean isrequired) {
        this.isrequired = isrequired;
    }

    public Boolean getIsmultiselect() {
        return ismultiselect;
    }

    public void setIsmultiselect(Boolean ismultiselect) {
        this.ismultiselect = ismultiselect;
    }

    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }

    public Boolean getIssearchableby() {
        return issearchableby;
    }

    public void setIssearchableby(Boolean issearchableby) {
        this.issearchableby = issearchableby;
    }

    public Boolean getIsusegreaterthanquery() {
        return isusegreaterthanquery;
    }

    public void setIsusegreaterthanquery(Boolean isusegreaterthanquery) {
        this.isusegreaterthanquery = isusegreaterthanquery;
    }

    public Boolean getIssingleton() {
        return issingleton;
    }

    public void setIssingleton(Boolean issingleton) {
        this.issingleton = issingleton;
    }


}
