package com.proship.omrs.contract.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.proship.omrs.base.entity.MainShardEntity;
import com.proship.omrs.candidate.candidate.entity.ParticipantAct;
import com.proship.omrs.candidate.candidate.param.ParticipantActSerializer;
import com.proship.omrs.gig.entity.Gig;
import com.proship.omrs.gig.param.GigSerializer;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name="contract_main_shard")
@Where(clause = "nexttransactiontime > current_date")
public class ContractMainShard extends MainShardEntity{
	
	
	ContractMainShard(){};
	
	@Id
	private Long id;

	//@JoinColumn(name="contractId")
	private Long contractId;

	@ManyToOne(fetch = FetchType.LAZY)
    @JsonSerialize(using = ParticipantActSerializer.class)
    private ParticipantAct act;

	@JsonIgnore
    private Long position;

    @ManyToOne
    @JsonSerialize(using = GigSerializer.class)
    private Gig gig;

    @ManyToOne
    @JoinColumn(name = "payId")
    private Pay pay;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public ParticipantAct getAct() {
		return act;
	}


	public void setAct(ParticipantAct act) {
		this.act = act;
	}

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
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


	  @Override
	public String toString() {
		return "ContractShard [id=" + id + ", act=" + act + ", position=" + position + ", gig=" + gig
				+ ", creator=" + super.getCreator() + ", validstarttime=" + super.getValidstarttime()+ ", validendtime="
                + super.getValidendtime() + ", pay=" + pay.getId() + "]";
	}
	
}
