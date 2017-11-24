package com.proship.omrs.contract.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;


@Entity
public class Contract implements Serializable{
	
	@Override
	public String toString() {
		return "Contract [id=" + id + ", specialty_type=" + specialtyType + ", non_ps=" + nonPs + ", uuid=" + uuid
				+ ", status=" + contractStatus.getCancel() + ", contractShards=" + contractMainShards + "]";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@JsonIgnore
	private Integer specialtyType;

	private Boolean nonPs;

	@JsonIgnore
	private Long uuid;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "contractId")
	@OrderBy(value = "validendtime")
	private List<ContractMainShard> contractMainShards;
	
	
	@OneToOne( mappedBy="contract",fetch = FetchType.LAZY)
	private ContractStatus contractStatus;

	
	Contract(){};

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSpecialtyType() {
		return specialtyType;
	}

	public void setSpecialtyType(Integer specialty_type) {
		this.specialtyType = specialty_type;
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
		return contractMainShards;
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
}
