package com.proship.omrs.contract.entity;

import com.proship.omrs.base.entity.BaseEntityWithCreator;
import com.proship.omrs.user.entity.User;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Where(clause = "nexttransactiontime>now()")
public class ContractRecruiterAttributionTts extends BaseEntityWithCreator{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="contract_recruiter_attribution_tts_id_seq")
    @SequenceGenerator(
            name="contract_recruiter_attribution_tts_id_seq",
            sequenceName="contract_recruiter_attribution_tts_id_sequence"
    )
    private Long id;

    @OneToOne
    private Contract contract;


    private Long recruiterId;

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

    public Long getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(Long recruiterId) {
        this.recruiterId = recruiterId;
    }
}
