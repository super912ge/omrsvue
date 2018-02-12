package com.proship.omrs.candidate.candidate.param;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.proship.omrs.contract.entity.ContractEvent;
import com.proship.omrs.contract.entity.ContractMainShard;
import com.proship.omrs.contract.param.ContractEventBrief;
import com.proship.omrs.gig.entity.Gig;
import com.proship.omrs.gig.param.GigBrief;
import com.proship.omrs.gig.param.GigSerializer;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ContractBrief {

    public ContractBrief(ContractMainShard contractMainShard) {

        this.id = contractMainShard.getContract().getId();
        if(contractMainShard.getContract().getJob()!=null)
        this.jobNumber = contractMainShard.getContract().getJob().getId();
        if (contractMainShard.getPay()!=null)
        this.salary = contractMainShard.getPay().getSalaryAmount()+contractMainShard.getPay().getSalaryCurrency();
        this.startDate = contractMainShard.getContract().getContractPeriodShard().getValidstarttime();
        this.endDate = contractMainShard.getContract().getContractPeriodShard().getValidendtime();
        this.gig = contractMainShard.getGig();
        this.events = contractMainShard.getContract().getContractEvents().stream().map(ContractEventBrief::new).collect(Collectors.toList());

    }


    private Long id;

    @JsonSerialize(using = GigSerializer.class)
    private Gig gig;

    private String salary;

    private Date startDate;

    private Date endDate;

    private Long jobNumber;

    private List<ContractEventBrief> events;

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
