package com.proship.omrs.contract.entity;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.proship.omrs.base.entity.BaseEntity;
import com.proship.omrs.jsonviews.UserSerializer;
import com.proship.omrs.user.entity.User;
import lombok.Data;

@Entity
@Data
@Table(name = "contract_status_cancel_tts")
public class ContractStatus extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="contract_cancelstatus_seq")
	@SequenceGenerator(
	    name="contract_cancelstatus_seq",
	    sequenceName="contract_status_cancel_tts_id_sequence"
	)
	private Long id;
	
	@ManyToOne(cascade= CascadeType.DETACH)
	  @JsonSerialize(using = UserSerializer.class)
	private User creator;
	
	@ManyToOne(cascade= CascadeType.DETACH)
	@JoinColumn(name="contract_id")
	@JsonIgnore
	private Contract contract;
	
	private Boolean cancel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public Boolean getCancel() {
		return cancel;
	}

	public void setCancel(Boolean cancel) {
		this.cancel = cancel;
	}
	
	
}
