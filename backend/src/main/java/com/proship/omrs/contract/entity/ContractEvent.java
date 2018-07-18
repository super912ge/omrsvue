package com.proship.omrs.contract.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.proship.omrs.contract.repository.ContractShardRepository;
import com.proship.omrs.jsonviews.UserSerializer;
import com.proship.omrs.user.entity.User;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;

import javax.annotation.Resource;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Entity


public class ContractEvent {

    @Transient
    ContractShardRepository contractShardRepository;

    @Transient
    private Properties contractEventCase;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="contract_event_id_seq")
    @SequenceGenerator(
            name="contract_event_id_seq",
            sequenceName="contract_event_id_sequence"
    )
    private Long id ;

    private Long uuid;

    @ManyToOne
    @JoinColumn(name = "commentId")
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "entityId")
    private Contract contract ;

    @CreationTimestamp
    private Timestamp transactionTime ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creatorId")
    @JsonSerialize(using = UserSerializer.class)
    private User creator;

    @OneToOne(mappedBy = "contractEvent",cascade = CascadeType.ALL)
    private ContractEventDateTts contractEventDateTts;

    @OneToOne(mappedBy = "contractEvent",cascade = CascadeType.ALL)
    private ContractEventCaseTts contractEventCaseTts;

    @OneToOne(mappedBy = "contractEvent",cascade = CascadeType.ALL)
    private ContractEventFormDateTts contractEventFormDateTts;

    @OneToOne(mappedBy = "contractEvent",cascade = CascadeType.ALL)
    private ContractEventTerritoryTts contractEventTerritoryTts;

    @OneToOne(mappedBy = "contractEvent",cascade = CascadeType.ALL)
    private ReasonCode reasonCode;

    @Transient
    private List<ContractMainShard> previousShards;

    @Transient
    private String name;

    @Transient
    private List<ContractMainShard> currentShards;


    public List<ContractMainShard> getPreviousShards() {

        String change = (String)contractEventCase.get("CHANGE");

        if (previousShards==null&&change.contains(this.name)){
            this.previousShards = contractShardRepository.
                    findByNexttransactiontimeAndContractId(this.transactionTime,this.contract.getId());
        }
        return previousShards;
    }

    public void setPreviousShards(List<ContractMainShard> previousShard) {
        this.previousShards = previousShard;
    }

    public List<ContractMainShard> getCurrentShards() {


        String change = (String)contractEventCase.get("CHANGE");
        if (currentShards==null&&change.contains(this.name)){
            this.currentShards = contractShardRepository.
                    findByTransactiontimeAndContractId(this.transactionTime,this.contract.getId());
        }
        return currentShards;
    }

    public void setCurrentShards(List<ContractMainShard> currentShard) {
        this.currentShards = currentShard;
    }

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

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Timestamp getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Timestamp transactionTime) {
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

    public ContractEventTerritoryTts getContractEventTerritoryTts() {
        return contractEventTerritoryTts;
    }

    public void setContractEventTerritoryTts(ContractEventTerritoryTts contractEventTerritoryTts) {
        this.contractEventTerritoryTts = contractEventTerritoryTts;
    }

    public ReasonCode getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(ReasonCode reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getName() {

        this.name = (String)contractEventCase.get(contractEventCaseTts.getValue());
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContractShardRepository getContractShardRepository() {
        return contractShardRepository;
    }

    public void setContractShardRepository(ContractShardRepository contractShardRepository) {
        this.contractShardRepository = contractShardRepository;
    }

    public Properties getContractEventCase() {
        return contractEventCase;
    }

    public void setContractEventCase(Properties contractEventCase) {
        this.contractEventCase = contractEventCase;
    }
}
