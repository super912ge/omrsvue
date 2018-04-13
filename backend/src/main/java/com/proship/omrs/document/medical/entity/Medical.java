package com.proship.omrs.document.medical.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.document.base.entity.Document;
import com.proship.omrs.utils.util.Utils;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Medical extends Document{

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="medical_id_seq")
    @SequenceGenerator(
            name="medical_id_seq",
            sequenceName="medical_id_sequence"
    )

    private Long id;

    @Transient
    private Date formDate;

    private Boolean fitForDuty;

    private Long medicalTypeId;

    @Transient
    private String comment;

    @Transient
    private String type;

    @JsonIgnore
    private Long participantId;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "medical")
    @Where(clause = "nexttransactiontime > now()")
    @JsonIgnore
    private MedicalDetail medicalDetail;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "medical")
    @Where(clause = "nexttransactiontime > now()")
    @JsonIgnore
    private MedicalFormDate medicalFormDate;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "medical")
    @Where(clause = "nexttransactiontime > now()")
    @JsonIgnore
    private MedicalComment medicalComment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public void setFormDate(Date formDate) {
        this.formDate = formDate;
    }

    public Boolean getFitForDuty() {
        return fitForDuty;
    }

    public void setFitForDuty(Boolean fitForDuty) {
        this.fitForDuty = fitForDuty;
    }

    public Long getMedicalTypeId() {
        return medicalTypeId;
    }

    public void setMedicalTypeId(Long medicalTypeId) {
        this.medicalTypeId = medicalTypeId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public Date getFormDate() {
        setFormDate(medicalFormDate.getValue());
        return formDate;
    }

    public String getComment() {
        setComment(medicalComment.getText());
        return comment;
    }

    public String getType() {
        setType(MedicalTypeMap.getMedicalType(medicalTypeId).getName());
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MedicalDetail getMedicalDetail() {
        return medicalDetail;
    }

    public void setMedicalDetail(MedicalDetail medicalDetail) {
        this.medicalDetail = medicalDetail;
    }

    public MedicalFormDate getMedicalFormDate() {
        return medicalFormDate;
    }

    public void setMedicalFormDate(MedicalFormDate medicalFormDate) {
        this.medicalFormDate = medicalFormDate;
    }

    public MedicalComment getMedicalComment() {
        return medicalComment;
    }

    public void setMedicalComment(MedicalComment medicalComment) {
        this.medicalComment = medicalComment;
    }
}
