package com.proship.omrs.candidate.group.param;

import com.proship.omrs.candidate.participant.entity.*;
import com.proship.omrs.evaluation.entity.EvalTag;

public class BandMemberBrief {



    private Long id;

    private String name;

    private EvalTag evaluation;

    private Long actId;


    public BandMemberBrief(Participant participant){
        this.id = participant.getId();
        this.actId = participant.getParticipantAct().getId();

        this.name = participant.getName();

        if (participant.getEvaluation()!=null) {
            this.evaluation = participant.getEvaluation();
        }
    }

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

    public EvalTag getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(EvalTag evaluation) {
        this.evaluation = evaluation;
    }

    public Long getActId() {
        return actId;
    }

    public void setActId(Long actId) {
        this.actId = actId;
    }
}
