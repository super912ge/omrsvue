package com.proship.omrs.contract.param;

import java.util.List;

public class ContractSearchResultParam {

    private Long totalPage;

    private List<ContractBriefWithParticipant> contracts;

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public List<ContractBriefWithParticipant> getContracts() {
        return contracts;
    }

    public void setContracts(List<ContractBriefWithParticipant> contracts) {
        this.contracts = contracts;
    }
}
