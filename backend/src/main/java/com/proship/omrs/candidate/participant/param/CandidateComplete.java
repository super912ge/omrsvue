package com.proship.omrs.candidate.participant.param;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.proship.omrs.candidate.availibility.param.Availability;
import com.proship.omrs.candidate.participant.entity.Participant;
import com.proship.omrs.candidate.event.param.Note;
import com.proship.omrs.candidate.group.entity.Act;
import com.proship.omrs.candidate.group.entity.GroupActMemberShard;
import com.proship.omrs.candidate.group.param.BandBrief;
import com.proship.omrs.contract.entity.Contract;
import com.proship.omrs.contract.entity.ContractMainShard;
import com.proship.omrs.contract.entity.Job;
import com.proship.omrs.document.base.param.DocumentBrief;
import com.proship.omrs.evaluation.entity.EvalTag;

import java.util.*;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CandidateComplete {


    public CandidateComplete(Participant participant){

        this.associations = new ArrayList<>();

        this.essentials = new Essentials(participant);

        if(!participant.getParticipantAct().getContractShards().isEmpty()) {

            psContracts = new ArrayList<>();

            nonPsContracts = new ArrayList<>();

            Map<Participant,JobDate> associatesMap = new HashMap<>();

            Map<Long,Contract> contractMap = new HashMap<>();

            for (ContractMainShard contractMainShard : participant.getParticipantAct().getContractShards()) {


                if (contractMainShard.getContract().getNonPs()) {

                    this.nonPsContracts.add(new ContractBrief(contractMainShard.getContract()));

                }else {

                    Job job = contractMainShard.getContract().getJob();

                    if (job != null){

                        List<Contract> contracts = job.getContracts();

                        for (Contract contract : contracts) {

                            if (contract.getContractMainShards().get(0).getAct() != null) {

                                Participant associate = contract.getContractMainShards().get(0).getAct().getParticipant();

                                if (!associate.getId().equals(participant.getId()) && associate.getParticipantAct().getId() != 2) {

                                    if (associatesMap.get(associate) != null) {

                                        Date currDate = associatesMap.get(associate).getStartDate();

                                        if (contract.getContractPeriodShard().getValidstarttime().after(currDate)) {
                                            JobDate jobDate = new JobDate();
                                            jobDate.setContractNumber(contract.getId());
                                            jobDate.setEndDate(contract.getContractPeriodShard().getValidendtime());
                                            jobDate.setStartDate(contract.getContractPeriodShard().getValidstarttime());
                                            jobDate.setJobNumber(job.getId());
                                            associatesMap.put(associate,jobDate);
                                        }
                                    } else {
                                        JobDate jobDate = new JobDate();
                                        jobDate.setContractNumber(contract.getId());
                                        jobDate.setEndDate(contract.getContractPeriodShard().getValidendtime());
                                        jobDate.setStartDate(contract.getContractPeriodShard().getValidstarttime());
                                        jobDate.setJobNumber(job.getId());
                                        associatesMap.put(associate, jobDate);
                                    }
                                }
                            }
                        }
                    }
                    contractMap.computeIfAbsent(contractMainShard.getContract().getId(),key -> contractMainShard.getContract());
                }
            }
            if (contractMap.size()>0) {
                for (Map.Entry<Long, Contract> contract : contractMap.entrySet()) {
                    ContractBrief contractBrief = new ContractBrief(contract.getValue());
                    this.psContracts.add(contractBrief);
                }
            }

            for (Participant p : associatesMap.keySet()){
                JobDate jobDate = associatesMap.get(p);
                this.associations.add(new Association(p,jobDate.startDate,jobDate.endDate,jobDate.contractNumber,jobDate.jobNumber));
            }
        }
        this.notes = participant.getEvents().stream().map(Note::new).collect(Collectors.toList());

        List<DocumentBrief> document = participant.getSeamansBooksList()
                .stream().map(DocumentBrief::new).collect(Collectors.toList());

        this.identifications = new ArrayList<>();

        this.medicals = new ArrayList<>();

        this.identifications.addAll(document);

        document = participant.getVisaList().stream().map(DocumentBrief::new).collect(Collectors.toList());

        this.identifications.addAll(document);

        document = participant.getPassportList().stream().map(DocumentBrief::new).collect(Collectors.toList());

        this.identifications.addAll(document);

        document = participant.getCertificateList().stream().map(DocumentBrief::new)
                .collect(Collectors.toList());

        this.identifications.addAll(document);

        document = participant.getMedicalList().stream().map(DocumentBrief::new).collect(Collectors.toList());

        this.medicals = document;

        this.evalTag = participant.getEvaluation();

      Set<Act> bands= participant.getParticipantAct().getBands().stream()
                .map(GroupActMemberShard::getAct).collect(Collectors.toSet());
      this.bands = bands.stream().map(BandBrief::new).collect(Collectors.toList());

    }

    private Essentials essentials;

    private List<Note> notes;

    private List<Availability> availabilities;

    private List<ContractBrief> psContracts;

    private List<ContractBrief>nonPsContracts;

    private List<DocumentBrief> identifications;

    private List<DocumentBrief> medicals;

    private List<Association> associations;

    private List<BandBrief> bands;

    private EvalTag evalTag;

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

    public List<DocumentBrief> getIdentifications() {
        return identifications;
    }

    public void setIdentifications(List<DocumentBrief> identifications) {
        this.identifications = identifications;
    }

    public List<DocumentBrief> getMedicals() {
        return medicals;
    }

    public void setMedicals(List<DocumentBrief> medicals) {
        this.medicals = medicals;
    }

    public List<Association> getAssociations() {
        return associations;
    }

    public void setAssociations(List<Association> associations) {
        this.associations = associations;
    }

    public List<BandBrief> getBands() {
        return bands;
    }

    public void setBands(List<BandBrief> bands) {
        this.bands = bands;
    }

    public EvalTag getEvalTag() {
        return evalTag;
    }

    public void setEvalTag(EvalTag evalTag) {
        this.evalTag = evalTag;
    }


    private class JobDate{

        Date startDate;

        Date endDate;

        Long jobNumber;

        Long contractNumber;

        public Date getStartDate() {
            return startDate;
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }

        public Date getEndDate() {
            return endDate;
        }

        public void setEndDate(Date endDate) {
            this.endDate = endDate;
        }

        public Long getJobNumber() {
            return jobNumber;
        }

        public void setJobNumber(Long jobNumber) {
            this.jobNumber = jobNumber;
        }

        public Long getContractNumber() {
            return contractNumber;
        }

        public void setContractNumber(Long contractNumber) {
            this.contractNumber = contractNumber;
        }
    }
}
