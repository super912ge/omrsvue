package com.proship.omrs.candidate.candidate.param;


import com.proship.omrs.contract.entity.Contract;
import com.proship.omrs.contract.entity.ContractMainShard;
import com.proship.omrs.contract.param.ContractEventBrief;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ContractBrief {

    public ContractBrief(Contract contract){
        this.id = contract.getId();
        this.shards = new ArrayList<>();
        for (ContractMainShard contractMainShard : contract.getContractMainShards()) {
            shards.add(new ContractShardBrief(contractMainShard));
        }
        if (contract.getJob()!=null)
        this.jobNumber = contract.getJob().getId();

        this.events = contract.getContractEvents().stream().map(ContractEventBrief::new).collect(Collectors.toList());

        Collections.sort(this.events, Comparator.comparing(ContractEventBrief::getTransactionTime));

    }

    private Long id;

    private List<ContractShardBrief> shards;

    private Long jobNumber;

    private List<ContractEventBrief> events;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ContractShardBrief> getShards() {
        return shards;
    }

    public void setShards(List<ContractShardBrief> shards) {
        this.shards = shards;
    }

    public Long getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(Long jobNumber) {
        this.jobNumber = jobNumber;
    }

    public List<ContractEventBrief> getEvents() {
        return events;
    }

    public void setEvents(List<ContractEventBrief> events) {
        this.events = events;
    }

}
