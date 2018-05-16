package com.proship.omrs.contract.param;

import com.proship.omrs.contract.entity.Contract;
import com.proship.omrs.contract.entity.ContractMainShard;

import java.util.ArrayList;
import java.util.List;

public class ContractBriefBase {

        public ContractBriefBase(Contract contract){
            this.id = contract.getId();

//            if (contract.getJob()!=null)
//                this.jobNumber = contract.getJob().getId();

            this.jobNumber = contract.getNumber();

            this.shards = new ArrayList<>();
            for (ContractMainShard contractMainShard : contract.getContractMainShards()) {
                shards.add(new com.proship.omrs.candidate.participant.param.ContractShardBrief(contractMainShard));
            }

        }

    private List<com.proship.omrs.candidate.participant.param.ContractShardBrief> shards;

    public List<com.proship.omrs.candidate.participant.param.ContractShardBrief> getShards() {
        return shards;
    }

    public void setShards(List<com.proship.omrs.candidate.participant.param.ContractShardBrief> shards) {
        this.shards = shards;
    }


        private Long id;

        private Long jobNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(Long jobNumber) {
        this.jobNumber = jobNumber;
    }
}
