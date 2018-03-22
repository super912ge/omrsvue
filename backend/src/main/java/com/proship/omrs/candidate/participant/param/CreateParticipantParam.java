package com.proship.omrs.candidate.participant.param;

import java.util.Date;

public class CreateParticipantParam {

    private String firstName;

    private String otherName;

    private String lastName;

    private String pronunciation;

    private String actName;

    private Date birthday;

    private String gender;

    public String getFirstName() {
        return firstName==null?"":this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getOtherName() {
        return otherName==null?"":this.otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPronunciation() {
        return pronunciation==null?"":this.pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String getActName() {
        return actName==null?"":this.actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}