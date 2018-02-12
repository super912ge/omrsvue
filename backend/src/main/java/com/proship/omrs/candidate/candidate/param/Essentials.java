package com.proship.omrs.candidate.candidate.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.proship.omrs.candidate.candidate.entity.*;
import com.proship.omrs.evaluation.entity.EvalTag;
import com.proship.omrs.system.airport.entity.Airport;
import com.proship.omrs.system.country.entity.Country;
import com.proship.omrs.utils.util.Utils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Essentials {

    public Essentials(Participant participant){

        this.name = participant.getName();

        this.birthDay = participant.getBirthDay();

        this.employeeNumber = participant.getId();

        if(participant.getCitizenship()!=null&&!participant.getCitizenship().isEmpty())
        this.citizenships = participant.getCitizenship().stream()
                .map(ParticipantCitizenshipOverride::getParticipantCitizenship).map(ParticipantCitizenship::getCountry)
                .map(Country::getName).collect(Collectors.toList());

        if(participant.getResidency()!=null&&!participant.getResidency().isEmpty())
            this.citizenships = participant.getResidency().stream()
                    .map(ParticipantResidencyOverride::getParticipantResidency)
                    .map(ParticipantResidency::getCountry).map(Country::getName).collect(Collectors.toList());

        if (participant.getContactFields()!=null &&!participant.getContactFields().isEmpty()){
           for (ParticipantContactFieldOverride cfo: participant.getContactFields()){

               ParticipantContactField cf = cfo.getParticipantContactField();

               if (cf.getContactFieldType().getId()==2 ){
                   if (cf.getContactFieldLabel()==null || cf.getContactFieldLabel().getId()==1)
                   this.mainPhone = cf.getValue();
               }
               if(cf.getContactFieldType().getId()==3){
                   if (cf.getContactFieldLabel()==null || cf.getContactFieldLabel().getId()==1)
                   this.mainMobile = cf.getValue();
               }
               if (cf.getContactFieldType().getId()==1){
                   this.email = cf.getValue();
               }
           }
        }

        this.primarySkill = Utils.findPrimarySkill(participant.getEvaluation()).getType().getLabel();

        if (participant.getHomeAirports()!=null && !participant.getHomeAirports().isEmpty()){

            this.homeAirports = participant.getHomeAirports().stream().map(ParticipantHomeAirportOverride::getParticipantHomeAirport)
                    .map(ParticipantHomeAirport::getAirport).collect(Collectors.toList());
        }

    }

    private String name;

    private String mainPhone;

    private String mainMobile;

    private String mainAddress;

    private String email;

    private List<String> citizenships;

    private List<String> residencys;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDay;

    private List<Airport> homeAirports;

    private String medicalCondition;

    private String criminalCondition;

    private Long employeeNumber;

    private String primarySkill;

    public String getPrimarySkill() {
        return primarySkill;
    }

    public void setPrimarySkill(String primarySkill) {
        this.primarySkill = primarySkill;
    }

    public String getMainPhone() {
        return mainPhone;
    }

    public void setMainPhone(String mainPhone) {
        this.mainPhone = mainPhone;
    }

    public String getMainMobile() {
        return mainMobile;
    }

    public void setMainMobile(String mainMobile) {
        this.mainMobile = mainMobile;
    }

    public String getMainAddress() {
        return mainAddress;
    }

    public void setMainAddress(String mainAddress) {
        this.mainAddress = mainAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getCitizenships() {
        return citizenships;
    }

    public void setCitizenships(List<String> citizenships) {
        this.citizenships = citizenships;
    }

    public List<String> getResidencys() {
        return residencys;
    }

    public void setResidencys(List<String> residencys) {
        this.residencys = residencys;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public List<Airport> getHomeAirports() {
        return homeAirports;
    }

    public void setHomeAirports(List<Airport> homeAirports) {
        this.homeAirports = homeAirports;
    }

    public String getMedicalCondition() {
        return medicalCondition;
    }

    public void setMedicalCondition(String medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    public String getCriminalCondition() {
        return criminalCondition;
    }

    public void setCriminalCondition(String criminalCondition) {
        this.criminalCondition = criminalCondition;
    }

    public Long getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(Long employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
