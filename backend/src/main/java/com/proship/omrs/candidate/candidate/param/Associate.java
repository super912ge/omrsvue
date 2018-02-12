package com.proship.omrs.candidate.candidate.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proship.omrs.candidate.candidate.entity.Participant;
import com.proship.omrs.evaluation.entity.EvalTag;
import com.proship.omrs.evaluation.entity.EvalTagType;
import com.proship.omrs.utils.util.Utils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Associate {

    public Associate(Participant participant, Date date){

        this.id = participant.getId();

        this.name = participant.getName();

        EvalTag primarySkill = Utils.findPrimarySkill(participant.getEvaluation());

        if (primarySkill!=null)
        this.primarySkill = primarySkill.getType().getLabel();
        List<EvalTagType> languages =  Utils.findLanguage(participant.getEvaluation());

        if (languages!=null && !languages.isEmpty())
        this.language = languages.
                stream().map(EvalTagType::getLabel).collect(Collectors.toList());

        this.workingDate = date;
    }

    private Long id;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date workingDate;

    private String primarySkill;

    private List<String> language;

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

    public Date getWorkingDate() {
        return workingDate;
    }

    public void setWorkingDate(Date workingDate) {
        this.workingDate = workingDate;
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


}
