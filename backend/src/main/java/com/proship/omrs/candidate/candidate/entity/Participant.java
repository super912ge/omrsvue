package com.proship.omrs.candidate.candidate.entity;


import com.proship.omrs.candidate.group.entity.GroupActMemberShard;
import com.proship.omrs.document.certificate.entity.Certificate;
import com.proship.omrs.document.medical.entity.Medical;
import com.proship.omrs.document.passport.entity.Passport;
import com.proship.omrs.document.seamanBook.entity.SeamansBook;
import com.proship.omrs.document.visa.entity.Visa;
import com.proship.omrs.evaluation.entity.EvalTag;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Table(name = "participant")
@Entity
public class Participant{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="participant_id_seq")
    @SequenceGenerator(
            name="participant_id_seq",
            sequenceName="participant_id_sequence"
    )
    private Long id;

    private Integer specialtyType ;

    private Long uuid ;

    @Transient
    private String name;

    @OneToOne(mappedBy = "participant")
    private ParticipantAct participantAct;

    @OneToOne
    @JoinColumn(name = "evaluationId")
    private EvalTag evaluation;

    @OneToMany(mappedBy = "leader")
    @Where(clause = "band.validendtime > current_date and band.nexttransactiontime > current_date")
    private Set<GroupActMemberShard> band;

//    @OneToMany
//    private List<ParticipantGender> gender;

    @OneToMany(mappedBy = "participant")
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

//    Long actimportflag bigint,
//    Timestamp act_import_time timestamp without time zone,
//    act_import_user bigint,


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

    public Set<GroupActMemberShard> getBand() {
        return band;
    }

    public void setBand(Set<GroupActMemberShard> band) {
        this.band = band;
    }

    public String getName() {

        this.name = this.participantAct.getParticipantActName().getName();
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
