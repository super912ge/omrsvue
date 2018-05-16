package com.proship.omrs.candidate.participant.param;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.proship.omrs.candidate.participant.entity.ParticipantAct;
import com.proship.omrs.contract.entity.Contract;
import com.proship.omrs.contract.param.ContractBriefBase;
import com.proship.omrs.contract.param.ContractEventBrief;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ContractBrief extends ContractBriefBase {

    public ContractBrief(Contract contract){
        super(contract);

        this.events = contract.getContractEvents().stream().map(ContractEventBrief::new).collect(Collectors.toList());

        Collections.sort(this.events, Comparator.comparing(ContractEventBrief::getTransactionTime));



    }


    private List<ContractEventBrief> events;

    public List<ContractEventBrief> getEvents() {
        return events;
    }

    public void setEvents(List<ContractEventBrief> events) {
        this.events = events;
    }



}
