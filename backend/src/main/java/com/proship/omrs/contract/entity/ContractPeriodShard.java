package com.proship.omrs.contract.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.base.entity.BaseEntityWithCreator;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Where(clause = "nexttransactiontime> now()")
public class ContractPeriodShard extends BaseEntityWithCreator{

    @Id
    private Long id;

    private Integer startConfidence;

    private Integer endConfidence ;

    @ManyToOne
    @JsonIgnore
    private Contract contract;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date validstarttime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date validendtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStartConfidence() {
        return startConfidence;
    }

    public void setStartConfidence(Integer startConfidence) {
        this.startConfidence = startConfidence;
    }

    public Integer getEndConfidence() {
        return endConfidence;
    }

    public void setEndConfidence(Integer endConfidence) {
        this.endConfidence = endConfidence;
    }

    public Date getValidstarttime() {
        return validstarttime;
    }

    public void setValidstarttime(Date validstarttime) {
        this.validstarttime = validstarttime;
    }

    public Date getValidendtime() {
        return validendtime;
    }

    public void setValidendtime(Date validendtime) {
        this.validendtime = validendtime;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
