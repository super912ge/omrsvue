package com.proship.omrs.contract.param;
import com.proship.omrs.candidate.participant.param.ParticipantActBriefWrapper;
import com.proship.omrs.contract.entity.Contract;

public class ContractBriefWithParticipant extends ContractBriefBase{

    public ContractBriefWithParticipant(Contract contract){

        super(contract);

        this.act = new ParticipantActBriefWrapper(contract.getContractMainShards().get(0).getAct());
    }


    private ParticipantActBriefWrapper act;

    public ParticipantActBriefWrapper getAct() {
        return act;
    }

    public void setAct(ParticipantActBriefWrapper act) {
        this.act = act;
    }
}
