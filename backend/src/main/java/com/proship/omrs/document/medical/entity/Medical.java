package com.proship.omrs.document.medical.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.document.base.entity.Document;
import com.proship.omrs.utils.util.Utils;

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
    private MedicalDetail detail;

    @Transient
    private Date formDate;

    private Long uuid;

    private Boolean  fitForDuty;

    private Long medicalTypeId;

    @Transient
    private String comment;

    @Transient
    private MedicalType medicalType;

    @JsonIgnore
    private Long participantId;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "medical")
    @OrderBy("transactiontime")
    private List<MedicalDetail> detailList;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "medical")
    @OrderBy("transactiontime")
    List<MedicalFormDate> formDateList;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "medical")
    @OrderBy("transactiontime")
    List<MedicalComment> commentList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MedicalDetail getDetail() {

        detail = Utils.getLastElementFromList(detailList);

        return detail;
    }

    public void setDetail(MedicalDetail detail) {
        this.detail = detail;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public Date getFormDate() {

        formDate = Utils.getLastElementFromList(formDateList).getValue();

        return formDate;
    }

    public void setFormDate(Date formDate) {
        this.formDate = formDate;
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
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

    public List<MedicalDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<MedicalDetail> detailList) {
        this.detailList = detailList;
    }

    public List<MedicalFormDate> getFormDateList() {
        return formDateList;
    }

    public void setFormDateList(List<MedicalFormDate> formDateList) {
        this.formDateList = formDateList;
    }

    public List<MedicalComment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<MedicalComment> commentList) {
        this.commentList = commentList;
    }

    public String getComment() {

        comment = Utils.getLastElementFromList(commentList).getText();

        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public MedicalType getMedicalType() {

        this.medicalType = MedicalTypeMap.getMedicalType(this.medicalTypeId);

        return medicalType;
    }

    public void setMedicalType(MedicalType medicalType) {
        this.medicalType = medicalType;
    }
}
