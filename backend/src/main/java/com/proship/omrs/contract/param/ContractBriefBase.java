package com.proship.omrs.contract.param;

import com.proship.omrs.contract.entity.Contract;
import com.proship.omrs.contract.entity.ContractMainShard;

import java.util.ArrayList;
import java.util.List;

public class ContractBriefBase {

        public ContractBriefBase(Contract contract){

            this.id = contract.getId();

            this.jobNumber = contract.getNumber();

            this.cancel = contract.getContractStatus().getCancel();

            this.shards = new ArrayList<>();

            for (ContractMainShard contractMainShard : contract.getContractMainShards()) {

                shards.add(new ContractShardBrief(contractMainShard));
            }

        }

    private List<ContractShardBrief> shards;

    public List<ContractShardBrief> getShards() {
        return shards;
    }

    public void setShards(List<ContractShardBrief> shards) {
        this.shards = shards;
    }


        private Long id;

        private String jobNumber;

        private Boolean cancel;


    public Boolean getCancel() {
        return cancel;
    }

    public void setCancel(Boolean cancel) {
        this.cancel = cancel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }


}
