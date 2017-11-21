package com.proship.omrs.contract.entity;

import java.sql.Timestamp;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.proship.omrs.base.entity.BaseEntity;
import com.proship.omrs.candidate.candidate.entity.ParticipantAct;
import com.proship.omrs.gig.entity.Gig;
import com.proship.omrs.jsonviews.UserSerializer;
import com.proship.omrs.user.entity.User;

import lombok.Data;

@Entity
@Data
@Table(name="contract_main_shard")
public class ContractMainShard extends BaseEntity{
	
	
	ContractMainShard(){};
	
	@Id
	Long id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="contract_id")
	Contract contract;

	@ManyToOne
    ParticipantAct participantAct;

    Long position;

    @ManyToOne
    Gig gig;

    @ManyToOne @JoinColumn(name = "pay_id")
    Pay pay;
	  
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="creator_id")
    @JsonSerialize(using = UserSerializer.class)
    User creator;
	  
    @JsonFormat(pattern="yyyy-MM-dd")
    Timestamp validstarttime;
    @JsonFormat(pattern="yyyy-MM-dd")
    Timestamp validendtime;


	Long pay_id;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Contract getContract() {
		return contract;
	}


	public void setContract(Contract contract) {
		this.contract = contract;
	}


	public ParticipantAct getParticipantAct() {
		return participantAct;
	}


	public void setParticipantAct(ParticipantAct act) {
		this.participantAct = act;
	}


	public Long getPosition() {
		return position;
	}


	public void setPosition(Long position) {
		this.position = position;
	}


	public Gig getGig() {
		return gig;
	}


	public void setGig(Gig gig) {
		this.gig = gig;
	}


	public User getCreator() {
		return creator;
	}


	public void setCreator(User creator) {
		this.creator = creator;
	}


	public Timestamp getValidstarttime() {
		return validstarttime;
	}


	public void setValidstarttime(Timestamp validstarttime) {

		this.validstarttime = validstarttime;
	}


	public Timestamp getValidendtime() {
		return validendtime;
	}


	public void setValidendtime(Timestamp validendtime) {
		this.validendtime = validendtime;
	}


	public Long getPay_id() {
		return pay_id;
	}


	public void setPay_id(Long pay_id) {
		this.pay_id = pay_id;
	}

	  @Override
	public String toString() {
		return "ContractShard [id=" + id + ", act=" + participantAct + ", position=" + position + ", gig=" + gig
				+ ", creator=" + creator + ", validstarttime=" + validstarttime + ", validendtime=" + validendtime
				+ ", pay_id=" + pay_id + "]";
	}
	
}
