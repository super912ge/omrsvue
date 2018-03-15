package com.proship.omrs.contract.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.base.entity.BaseEntityTts;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;
@Entity
@Where(clause = "nexttransactiontime > current_date")
public class ContractEventDateTts extends BaseEntityTts<Date> {

    @Id
    private Long id;

    @OneToOne @JsonIgnore
    ContractEvent contractEvent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ContractEvent getContractEvent() {
        return contractEvent;
    }

    public void setContractEvent(ContractEvent contractEvent) {
        this.contractEvent = contractEvent;
    }
}
