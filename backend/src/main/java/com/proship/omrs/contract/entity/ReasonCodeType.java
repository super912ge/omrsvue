package com.proship.omrs.contract.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ReasonCodeType {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "reasonCodeMetaTypeId"
    )
    private ReasonCodeMetaType reasonCodeMetaType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    ReasonCodeType superType;

    @OneToMany(mappedBy = "superType")
    List<ReasonCodeType> subTypes;

    private String contractEventCase;
    private Boolean multiselect;
    private Boolean enabled ;
    private Integer order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReasonCodeMetaType getReasonCodeMetaType() {
        return reasonCodeMetaType;
    }

    public void setReasonCodeMetaType(ReasonCodeMetaType reasonCodeMetaType) {
        this.reasonCodeMetaType = reasonCodeMetaType;
    }

    public ReasonCodeType getSuperType() {
        return superType;
    }

    public void setSuperType(ReasonCodeType superType) {
        this.superType = superType;
    }

    public List<ReasonCodeType> getSubTypes() {
        return subTypes;
    }

    public void setSubTypes(List<ReasonCodeType> subTypes) {
        this.subTypes = subTypes;
    }

    public String getContractEventCase() {
        return contractEventCase;
    }

    public void setContractEventCase(String contractEventCase) {
        this.contractEventCase = contractEventCase;
    }

    public Boolean getMultiselect() {
        return multiselect;
    }

    public void setMultiselect(Boolean multiselect) {
        this.multiselect = multiselect;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
