package com.proship.omrs.contract.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.proship.omrs.contract.entity.ContractMainShard;
import com.proship.omrs.gig.param.GigBrief;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ContractShardBrief {

    public ContractShardBrief(ContractMainShard contractMainShard) {

        this.id = contractMainShard.getContract().getId();

        if (contractMainShard.getPay()!=null) {
            this.salary = contractMainShard.getPay().getSalaryAmount()+" " + contractMainShard.getPay().getSalaryCurrency();
        }
        this.startDate = contractMainShard.getValidstarttime();
        this.endDate = contractMainShard.getValidendtime();
        this.gig = new GigBrief(contractMainShard.getGig(), contractMainShard.getValidstarttime(),
                contractMainShard.getValidendtime());

        this.rank = contractMainShard.getRank().getName();
    }

    private Long id;

    private GigBrief gig;

    private String salary;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private String rank;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public GigBrief getGig() {
        return gig;
    }

    public void setGig(GigBrief gig) {
        this.gig = gig;
    }
}
