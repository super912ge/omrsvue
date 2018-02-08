package com.proship.omrs.candidate.candidate.param;


import com.proship.omrs.candidate.candidate.entity.Participant;
import com.proship.omrs.contract.entity.ContractMainShard;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CandidateComplete {

    public CandidateComplete(Participant participant){

        this.essentials = new Essentials(participant);

        if(!participant.getParticipantAct().getContractShards().isEmpty()) {

            psContracts = new ArrayList<>();
            nonPsContracts = new ArrayList<>();
            for (ContractMainShard contractMainShard : participant.getParticipantAct().getContractShards()) {

                ContractBrief contractBrief = new ContractBrief(contractMainShard);
                if (contractMainShard.getContract().getNonPs()) {
                    this.nonPsContracts.add(contractBrief);
                }else this.psContracts.add(contractBrief);
            }
        }
        this.notes = participant.getEvents().stream().map(Note::new).collect(Collectors.toList());

    }

    private Essentials essentials;

    private List<Note> notes;

    private List<Availability> availabilities;

    private List<ContractBrief> psContracts;

    private List<ContractBrief>nonPsContracts;

    public Essentials getEssentials() {
        return essentials;
    }

    public void setEssentials(Essentials essentials) {
        this.essentials = essentials;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public List<Availability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<Availability> availabilities) {
        this.availabilities = availabilities;
    }

    public List<ContractBrief> getPsContracts() {
        return psContracts;
    }

    public void setPsContracts(List<ContractBrief> psContracts) {
        this.psContracts = psContracts;
    }

    public List<ContractBrief> getNonPsContracts() {
        return nonPsContracts;
    }

    public void setNonPsContracts(List<ContractBrief> nonPsContracts) {
        this.nonPsContracts = nonPsContracts;
    }
}
