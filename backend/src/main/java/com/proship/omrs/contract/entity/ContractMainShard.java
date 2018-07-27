package com.proship.omrs.contract.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.base.entity.MainShardEntityWithGig;
import com.proship.omrs.candidate.participant.entity.ParticipantAct;
import com.proship.omrs.gig.entity.Gig;
import com.proship.omrs.gig.entity.Position;
import com.proship.omrs.gig.entity.PositionMap;

import javax.persistence.*;

@Entity
@Table(name="contract_main_shard")
public class ContractMainShard extends MainShardEntityWithGig{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="contract_main_shard_id_seq")
	@SequenceGenerator(
			name="contract_main_shard_id_seq",
			sequenceName="contract_main_shard_id_sequence"
	)
	private Long id;

	//@JoinColumn(name="contractId")
	@ManyToOne
	@JsonIgnore
	private Contract contract;

	@ManyToOne(fetch = FetchType.LAZY)
    private ParticipantAct act;

	@JsonIgnore
    private Long position;

	@Transient
    private Position rank;

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
		return "ContractShard [id=" + id + ", act=" + act + ", position=" + position + ", gig=" + super.getGig()
				+ ", creator=" + super.getCreator() + ", validstarttime=" + super.getValidstarttime()+ ", validendtime="
                + super.getValidendtime() + ", pay=" + pay.getId() + "]";
	}

//	@PostLoad
//	public void postLoad(){
//		try {
//			if(this.getAct() != null && this.getAct().getId() == 2 || this.getAct().getId()==1
//					|| this.getAct().getId()==2577 || this.getAct().getId()==2576){
//				setAct(null);
//			}
//		}
//		catch (EntityNotFoundException e){
//			setAct(null);
//		}catch (Exception e){
//			e.printStackTrace();
//			throw e;
//		}
//	}
	
}
