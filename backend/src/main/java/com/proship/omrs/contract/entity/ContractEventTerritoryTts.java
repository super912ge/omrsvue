package com.proship.omrs.contract.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.proship.omrs.base.entity.BaseEntityWithCreator;
import com.proship.omrs.jsonviews.UserSerializer;
import com.proship.omrs.user.entity.User;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Where(clause = "nexttransactiontime> now()")
public class ContractEventTerritoryTts extends BaseEntityWithCreator{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="contract_event_territory_tts_id_seq")
    @SequenceGenerator(
            name="contract_event_territory_tts_id_seq",
            sequenceName="contract_event_territory_tts_id_sequence"
    )
    private Long id;

    @OneToOne
    @JsonIgnore
    private ContractEvent contractEvent;

    @ManyToOne
    @JsonSerialize(using = UserSerializer.class)
    @JoinColumn(name = "userId")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
