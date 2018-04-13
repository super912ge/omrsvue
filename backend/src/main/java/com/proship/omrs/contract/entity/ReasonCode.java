package com.proship.omrs.contract.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.base.entity.BaseEntityWithCreator;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Where(clause = "nexttransactiontime > now()")
public class ReasonCode extends BaseEntityWithCreator {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reasonCodeTypeId")
    private ReasonCodeType reasonCodeType;

    @OneToOne
    @JsonIgnore
    private ContractEvent contractEvent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReasonCodeType getReasonCodeType() {
        return reasonCodeType;
    }

    public void setReasonCodeType(ReasonCodeType reasonCodeType) {
        this.reasonCodeType = reasonCodeType;
    }

    public ContractEvent getContractEvent() {
        return contractEvent;
    }

    public void setContractEvent(ContractEvent contractEvent) {
        this.contractEvent = contractEvent;
    }
}
