package com.proship.omrs.contract.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.*;


@Entity
public class Contract implements Serializable{
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@JsonIgnore
	private Integer specialtyType;

	private Boolean nonPs;

	@ManyToOne
	@JoinTable(name = "Job_Contracts",
			joinColumns = {@JoinColumn(name="contract_id")},
			inverseJoinColumns = {@JoinColumn(name="job_id")})
	private Job job;

	@JsonIgnore
	private Long uuid;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "contractId")
    @Where(
            clause = "nexttransactiontime > now()"
    )
	@OrderBy(value = "validendtime")
	private List<ContractMainShard> contractMainShards;

	@OneToOne(mappedBy = "contract")
	private ContractPeriodShard contractPeriodShard;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contract")
	private List<ContractEvent> contractEvents;
	
	@OneToOne( mappedBy="contract",fetch = FetchType.LAZY)
	private ContractStatus contractStatus;

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSpecialtyType() {
		return specialtyType;
	}

	public void setSpecialtyType(Integer specialtyType) {
		this.specialtyType = specialtyType;
	}

	public Boolean getNonPs() {
		return nonPs;
	}

	public void setNonPs(Boolean non_ps) {
		this.nonPs = non_ps;
	}

	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	public List<ContractMainShard> getContractMainShards() {

	    this.contractMainShards = contractMainShards.stream().filter(item->item.getNexttransactiontime().after(new Date())).collect(Collectors.toList());
        return this.contractMainShards;
	}

	public void setContractMainShards(List<ContractMainShard> contractMainShards) {
		this.contractMainShards = contractMainShards;
	}

	public ContractStatus getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(ContractStatus contractStatus) {
		this.contractStatus = contractStatus;
	}

	public List<ContractEvent> getContractEvents() {
		return contractEvents;
	}

	public void setContractEvents(List<ContractEvent> contractEvents) {
		this.contractEvents = contractEvents;
	}

    public ContractPeriodShard getContractPeriodShard() {
        return contractPeriodShard;
    }

    public void setContractPeriodShard(ContractPeriodShard contractPeriodShard) {
        this.contractPeriodShard = contractPeriodShard;
    }
}
