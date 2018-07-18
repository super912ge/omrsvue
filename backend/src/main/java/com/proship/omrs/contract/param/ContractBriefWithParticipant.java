package com.proship.omrs.contract.param;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.proship.omrs.candidate.participant.entity.ParticipantAct;
import com.proship.omrs.candidate.participant.param.ParticipantActSerializer;
import com.proship.omrs.contract.entity.Contract;

public class ContractBriefWithParticipant extends ContractBriefBase{

    public ContractBriefWithParticipant(Contract contract){

        super(contract);

        this.act = contract.getContractMainShards().get(0).getAct();
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
