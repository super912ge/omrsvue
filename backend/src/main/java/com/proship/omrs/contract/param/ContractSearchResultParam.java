package com.proship.omrs.contract.param;

import java.util.List;

public class ContractSearchResultParam {

    private Long totalPage;

    private List<ContractBrief> contracts;

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public List<ContractBrief> getContracts() {
        return contracts;
    }

    public void setContracts(List<ContractBrief> contracts) {
        this.contracts = contracts;
    }
}
