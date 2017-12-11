package com.proship.omrs.candidate.candidate.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.proship.omrs.candidate.candidate.entity.*;
import com.proship.omrs.evaluation.entity.EvalTag;
import com.proship.omrs.system.country.entity.Country;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CandidateBrief {

    public CandidateBrief(Participant participant){
        this.id = participant.getId();
        this.actId = participant.getParticipantAct().getId();
        if (participant.getParticipantBirthdayTts()!=null) {
            this.birthDay = participant.getParticipantBirthdayTts().getValue();
        }
        this.name = participant.getName();
        if (participant.getAvailability()!=null) {
            DateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
            this.nextAvailableDate = dft.format(participant.getAvailability().getValidstarttime()) + " to "
                    + dft.format(participant.getAvailability().getValidendtime()) + ". Interest level: " + participant.getAvailability().getLevel();
        }
        if (participant.getEvaluation()!=null) {
            this.evaluation = participant.getEvaluation();
        }
        if(participant.getResidency()!=null) {
            this.residency = participant.getResidency().stream().map(ParticipantResidencyOverride::getParticipantResidency).
                    map(ParticipantResidency::getCountry).map(Country::getName).collect(Collectors.toList());
        }
        if(participant.getCitizenship()!=null) {
            this.citizenship = participant.getCitizenship().stream().map(ParticipantCitizenshipOverride::getParticipantCitizenship).
                    map(ParticipantCitizenship::getCountry).map(Country::getName).collect(Collectors.toList());
        }
    }

    private String name;

    private Long id;

    private Long actId;

    private EvalTag evaluation;

    private List<String> residency;

    private List<String> citizenship;

    private String nextAvailableDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDay;

    public String getName() {
        return name;
    }

    public EvalTag getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(EvalTag evaluation) {
        this.evaluation = evaluation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActId() {
        return actId;
    }

    public void setActId(Long actId) {
        this.actId = actId;
    }

    public List<String> getResidency() {
        return residency;
    }

    public void setResidency(List<String> residency) {
        this.residency = residency;
    }

    public List<String> getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(List<String> citizenship) {
        this.citizenship = citizenship;
    }

    public String getNextAvailableDate() {
        return nextAvailableDate;
    }

    public void setNextAvailableDate(String nextAvailableDate) {
        this.nextAvailableDate = nextAvailableDate;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
}
