package com.proship.omrs.contract.entity;

import com.proship.omrs.user.entity.User;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ContractEvent {

    @Id
    private Long id ;

    private Long uuid;

    @OneToOne(mappedBy = "contractEvent")
    private Comment comment;

    private Long entityId ;

    private Date transactionTime ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creatorId")
    private User creator;

    @OneToOne(mappedBy = "contractEvent")
    private ContractEventDateTts contractEventDateTts;

    @OneToOne(mappedBy = "contractEvent")
    private ContractEventCaseTts contractEventCaseTts;

    @OneToOne(mappedBy = "contractEvent")
    private ContractEventFormDateTts contractEventFormDateTts;

    @OneToOne(mappedBy = "contractEvent")
    private ContractEventTerritoryTts contractEventTorritoryTts;

    @OneToOne(mappedBy = "contractEvent")
    private ReasonCode reasonCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public ContractEventDateTts getContractEventDateTts() {
        return contractEventDateTts;
    }

    public void setContractEventDateTts(ContractEventDateTts contractEventDateTts) {
        this.contractEventDateTts = contractEventDateTts;
    }

    public ContractEventCaseTts getContractEventCaseTts() {
        return contractEventCaseTts;
    }

    public void setContractEventCaseTts(ContractEventCaseTts contractEventCaseTts) {
        this.contractEventCaseTts = contractEventCaseTts;
    }

    public ContractEventFormDateTts getContractEventFormDateTts() {
        return contractEventFormDateTts;
    }

    public void setContractEventFormDateTts(ContractEventFormDateTts contractEventFormDateTts) {
        this.contractEventFormDateTts = contractEventFormDateTts;
    }

    public ContractEventTerritoryTts getContractEventTorritoryTts() {
        return contractEventTorritoryTts;
    }

    public void setContractEventTorritoryTts(ContractEventTerritoryTts contractEventTorritoryTts) {
        this.contractEventTorritoryTts = contractEventTorritoryTts;
    }

    public ReasonCode getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(ReasonCode reasonCode) {
        this.reasonCode = reasonCode;
    }
}
