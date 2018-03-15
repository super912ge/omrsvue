package com.proship.omrs.contract.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.proship.omrs.base.entity.MainShardEntity;
import com.proship.omrs.candidate.participant.entity.ParticipantAct;
import com.proship.omrs.candidate.participant.param.ParticipantActSerializer;
import com.proship.omrs.gig.entity.Gig;
import com.proship.omrs.gig.entity.Position;
import com.proship.omrs.gig.entity.PositionMap;
import com.proship.omrs.gig.param.GigSerializer;

import javax.persistence.*;

@Entity
@Table(name="contract_main_shard")
public class ContractMainShard extends MainShardEntity{
	
	
	ContractMainShard(){};
	
	@Id
	private Long id;

	@JoinColumn(name="contractId")

	@ManyToOne
	@JsonIgnore

	private Contract contract;

	@ManyToOne(fetch = FetchType.LAZY)
    @JsonSerialize(using = ParticipantActSerializer.class)
    private ParticipantAct act;

	@JsonIgnore
    private Long position;

	@Transient
    private Position rank;

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

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public Pay getPay() {
		return pay;
	}

	public void setPay(Pay pay) {
		this.pay = pay;
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

    public Position getRank() {

	    this.rank = PositionMap.getPosition(position);
        return rank;
    }

    public void setRank(Position rank) {
        this.rank = rank;
        this.position = rank.getId();
    }

    @Override
	public String toString() {
		return "ContractShard [id=" + id + ", act=" + act + ", position=" + position + ", gig=" + gig
				+ ", creator=" + super.getCreator() + ", validstarttime=" + super.getValidstarttime()+ ", validendtime="
                + super.getValidendtime() + ", pay=" + pay.getId() + "]";
	}

	@PostLoad
	public void postLoad(){
		try {
			if(this.getAct() != null && this.getAct().getId() == 2 || this.getAct().getId()==1
					|| this.getAct().getId()==2577 || this.getAct().getId()==2576){
				setAct(null);
			}
		}
		catch (EntityNotFoundException e){
			setAct(null);
		}
	}
	
}
