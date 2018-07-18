package com.proship.omrs.contract.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.base.entity.BaseEntityTts;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Where(clause = "nexttransactiontime > now()")
public class ContractEventCaseTts extends BaseEntityTts<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="contract_event_case_tts_id_seq")
    @SequenceGenerator(
            name="contract_event_case_tts_id_seq",
            sequenceName="contract_event_case_tts_id_sequence"
    )
    private Long id;

    @OneToOne @JsonIgnore
    private ContractEvent contractEvent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ContractEvent getContractEvent() {
        return contractEvent;
    }

    public void setContractEvent(ContractEvent contractEvent) {
        this.contractEvent = contractEvent;
    }
}
