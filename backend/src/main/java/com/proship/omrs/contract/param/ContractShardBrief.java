package com.proship.omrs.contract.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.proship.omrs.contract.entity.ContractMainShard;
import com.proship.omrs.gig.entity.Gig;
import com.proship.omrs.gig.param.GigSerializer;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ContractShardBrief {

    public ContractShardBrief(ContractMainShard contractMainShard) {

        this.id = contractMainShard.getContract().getId();

        if(contractMainShard.getContract().getJob()!=null)

        if (contractMainShard.getPay()!=null) {
            this.salary = contractMainShard.getPay().getSalaryAmount() + contractMainShard.getPay().getSalaryCurrency();
        }
        this.startDate = contractMainShard.getValidstarttime();
        this.endDate = contractMainShard.getValidendtime();
        this.gig = contractMainShard.getGig();

        this.rank = contractMainShard.getRank().getName();
    }

    private Long id;

    @JsonSerialize(using = GigSerializer.class)
    private Gig gig;

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

    public Gig getGig() {
        return gig;
    }

    public void setGig(Gig gig) {
        this.gig = gig;
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
}
