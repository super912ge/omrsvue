package com.proship.omrs.candidate.participant.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proship.omrs.candidate.participant.entity.Participant;
import com.proship.omrs.evaluation.entity.EvalTag;
import com.proship.omrs.evaluation.entity.EvalTagType;
import com.proship.omrs.utils.util.Utils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Association {

    public Association(Participant participant, Date startDate,Date endDate, Long contractId, Long jobId){

        this.id = participant.getId();

        this.name = participant.getName();

        EvalTag primarySkill = Utils.findPrimarySkill(participant.getEvaluation());

        if (primarySkill!=null)
        this.primarySkill = primarySkill.getType().getLabel();
        List<EvalTagType> languages =  Utils.findLanguage(participant.getEvaluation());

        if (languages!=null && !languages.isEmpty())
        this.language = languages.
                stream().map(EvalTagType::getLabel).collect(Collectors.toList());

        this.startDate = startDate;

        this.endDate = endDate;

        this.contractId = contractId;

        this.jobNumber = jobId;

    }

    private Long id;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private String primarySkill;

    private List<String> language;

    private Long contractId;

    private Long jobNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getPrimarySkill() {
        return primarySkill;
    }

    public void setPrimarySkill(String primarySkill) {
        this.primarySkill = primarySkill;
    }

    public List<String> getLanguage() {
        return language;
    }

    public void setLanguage(List<String> language) {
        this.language = language;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Long getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(Long jobNumber) {
        this.jobNumber = jobNumber;
    }
}
