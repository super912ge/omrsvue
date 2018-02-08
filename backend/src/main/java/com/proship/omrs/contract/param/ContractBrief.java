package com.proship.omrs.contract.param;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.proship.omrs.candidate.candidate.entity.ParticipantAct;
import com.proship.omrs.candidate.candidate.param.ParticipantActSerializer;
import com.proship.omrs.contract.entity.ContractMainShard;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ContractBrief extends com.proship.omrs.candidate.candidate.param.ContractBrief{

    public ContractBrief(ContractMainShard contractMainShard) {
        super(contractMainShard);

        this.act = contractMainShard.getAct();
    }

    @JsonSerialize(using = ParticipantActSerializer.class)
    private ParticipantAct act;

    public ParticipantAct getAct() {
        return act;
    }

    public void setAct(ParticipantAct act) {
        this.act = act;
    }

}
