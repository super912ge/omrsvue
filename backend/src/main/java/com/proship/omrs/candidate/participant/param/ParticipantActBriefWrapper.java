package com.proship.omrs.candidate.participant.param;


import com.proship.omrs.candidate.participant.entity.ParticipantAct;

public class ParticipantActBriefWrapper{


    public ParticipantActBriefWrapper(ParticipantAct act){

        this.actId = act.getId();

        this.actName = act.getParticipantActName().getName();

        this.id = act.getParticipant().getId();

        this.fullName = act.getParticipant().getNameTts().getFullName();

    }


    private long id;

    private String actName;

    private long actId;

    private String fullName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public long getActId() {
        return actId;
    }

    public void setActId(long actId) {
        this.actId = actId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
