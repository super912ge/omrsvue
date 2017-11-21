package com.proship.omrs.contract.entity;


import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;


@Entity
//@Data
//@FieldDefaults(level = AccessLevel.PRIVATE)
public class Contract implements Serializable{
	
	@Override
	public String toString() {
		return "Contract [id=" + id + ", specialty_type=" + specialty_type + ", non_ps=" + non_ps + ", uuid=" + uuid
				+ ", status=" + status + ", contractShard=" + contractMainShard + "]";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	Long id;
	
	Integer specialty_type;
	
	Boolean non_ps;
	
	Long uuid;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy="contract")
	@OrderBy("transactiontime")
	List<ContractMainShard> contractMainShardList;
	
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy="contract")
	@OrderBy("transactiontime")
	List<ContractStatus> contractStatusList;
	
	
	// contract current status 
	@Transient
	Boolean status; 
	
	@Transient
	ContractMainShard contractMainShard;
	
	Contract(){};

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSpecialty_type() {
		return specialty_type;
	}

	public void setSpecialty_type(Integer specialty_type) {
		this.specialty_type = specialty_type;
	}

	public Boolean getNon_ps() {
		return non_ps;
	}

	public void setNon_ps(Boolean non_ps) {
		this.non_ps = non_ps;
	}

	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	public List<ContractMainShard> getContractShardList() {
		return contractMainShardList;
	}

	public void setContractShardList(List<ContractMainShard> contractShard) {
		this.contractMainShardList = contractShard;
	}

	public List<ContractStatus> getContractStatusList() {
		return contractStatusList;
	}

	public void setContractStatusList(List<ContractStatus> contractStatusList) {
		this.contractStatusList = contractStatusList;
	}

	public Boolean getStatus() {
		
		if(contractStatusList!=null&&!contractStatusList.isEmpty())
		
		status = contractStatusList.get(contractStatusList.size()-1).getCancel();
		return status;
	}
	public ContractMainShard getContractShard(){
		
		
		if(contractMainShardList!=null && !contractMainShardList.isEmpty()){
			contractMainShard = contractMainShardList.get(contractMainShardList.size()-1);
		}
		return contractMainShard;
		
	}
	public void setContractShard(ContractMainShard shard){
		 this.contractMainShard = shard;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}	
	
}
