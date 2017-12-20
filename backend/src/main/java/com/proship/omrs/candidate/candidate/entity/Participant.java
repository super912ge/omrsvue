package com.proship.omrs.candidate.candidate.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.proship.omrs.candidate.group.entity.GroupActMainShard;
import com.proship.omrs.candidate.group.entity.GroupActMemberShard;
import com.proship.omrs.document.certificate.entity.Certificate;
import com.proship.omrs.document.medical.entity.Medical;
import com.proship.omrs.document.passport.entity.Passport;
import com.proship.omrs.document.seamanBook.entity.SeamansBook;
import com.proship.omrs.document.visa.entity.Visa;
import com.proship.omrs.evaluation.entity.EvalTag;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Table(name = "participant")
@Entity
@JsonInclude(NON_NULL)

@Where(clause = "id not in (1,2576)")
public class Participant{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="participant_id_seq")
    @SequenceGenerator(
            name="participant_id_seq",
            sequenceName="participant_id_sequence"
    )
    private Long id;

    @JsonIgnore
    private Integer specialtyType ;
    @JsonIgnore
    private Long uuid ;

    @OneToOne(mappedBy = "participant")
    ParticipantNameTts nameTts;

    @Transient
    private String name;

    @OneToOne(mappedBy = "participant")
    private ParticipantAct participantAct;

    @OneToOne
    @JoinColumn(name = "evaluationId")
    private EvalTag evaluation;

    @OneToMany(mappedBy = "leader", fetch = FetchType.LAZY)
    //@JsonIgnore
    private Set<GroupActMainShard> groupActMainShards;

//    @OneToMany
//    private List<ParticipantGender> gender;

    @OneToMany(mappedBy = "participant",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ParticipantAct> participantActList;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "participantId")
    @OrderBy("id")
    private List<Passport> passportList;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "participantId")
    @OrderBy("id")
    private List<SeamansBook> seamansBooksList;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "participantId")
    @OrderBy("id")
    private List<Medical> medicalList;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "participantId")
    @OrderBy("id")
    private List<Certificate> certificateList;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "participantId")
    @OrderBy("id")
    private List<Visa> visaList;

    @OneToMany(mappedBy = "participant")
    private List<ParticipantAvailabilityBts> availability;

    @OneToOne(mappedBy = "participant")
    private ParticipantBirthdayTts participantBirthdayTts;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "participant")
    private List<ParticipantResidencyOverride> residency;

//    Long actimportflag bigint,
//    Timestamp act_import_time timestamp without time zone,
//    act_import_user bigint,

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "participant")
    @Where(clause = "nexttransactiontime > current_date")
    private List<ParticipantCitizenshipOverride> citizenship;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSpecialtyType() {
        return specialtyType;
    }

    public void setSpecialtyType(Integer specialtyType) {
        this.specialtyType = specialtyType;
    }

    public List<ParticipantAct> getParticipantActList() {
        return participantActList;
    }

    public void setParticipantActList(List<ParticipantAct> participantActList) {
        this.participantActList = participantActList;
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public EvalTag getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(EvalTag evaluation) {
        this.evaluation = evaluation;
    }

    public List<Passport> getPassportList() {
        return passportList;
    }

    public void setPassportList(List<Passport> passportList) {
        this.passportList = passportList;
    }

    public List<SeamansBook> getSeamansBooksList() {
        return seamansBooksList;
    }

    public void setSeamansBooksList(List<SeamansBook> seamansBooksList) {
        this.seamansBooksList = seamansBooksList;
    }

    public List<Medical> getMedicalList() {
        return medicalList;
    }

    public void setMedicalList(List<Medical> medicalList) {
        this.medicalList = medicalList;
    }

    public List<Certificate> getCertificateList() {
        return certificateList;
    }

    public void setCertificateList(List<Certificate> certificateList) {
        this.certificateList = certificateList;
    }

    public List<Visa> getVisaList() {
        return visaList;
    }

    public void setVisaList(List<Visa> visaList) {
        this.visaList = visaList;
    }

    public ParticipantAct getParticipantAct() {
        return participantAct;
    }

    public void setParticipantAct(ParticipantAct participantAct) {
        this.participantAct = participantAct;
    }

    public ParticipantNameTts getNameTts() {
        return nameTts;
    }

    public void setNameTts(ParticipantNameTts nameTts) {
        this.nameTts = nameTts;
    }

    public String getName() {
        if(this.nameTts!=null){
        this.name = this.nameTts.getFirstName()+" "+nameTts.getOtherName()+" " +nameTts.getLastName();}
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<GroupActMainShard> getGroupActMainShards() {
        return groupActMainShards;
    }

    public void setGroupActMainShards(Set<GroupActMainShard> groupActMainShards) {
        this.groupActMainShards = groupActMainShards;
    }

    public List<ParticipantAvailabilityBts> getAvailability() {
        return availability;
    }

    public void setAvailability(List<ParticipantAvailabilityBts> availability) {
        this.availability = availability;
    }

    public List<ParticipantResidencyOverride> getResidency() {
       this.residency =  this.residency.stream().filter(item -> item.getNexttransactiontime()
               .after(new Timestamp(System.currentTimeMillis()))).collect(Collectors.toList());
        return residency;
    }

    public void setResidency(List<ParticipantResidencyOverride> residency) {
        this.residency = residency;
    }

    public ParticipantBirthdayTts getParticipantBirthdayTts() {
        return participantBirthdayTts;
    }

    public void setParticipantBirthdayTts(ParticipantBirthdayTts participantBirthdayTts) {
        this.participantBirthdayTts = participantBirthdayTts;
    }

    public List<ParticipantCitizenshipOverride> getCitizenship() {

       this.citizenship =  citizenship.stream().filter(item->item.getNexttransactiontime()
                .after(new Timestamp(System.currentTimeMillis()))).collect(Collectors.toList());

        return citizenship;
    }

    public void setCitizenship(List<ParticipantCitizenshipOverride> citizenship) {
        this.citizenship = citizenship;
    }
}
