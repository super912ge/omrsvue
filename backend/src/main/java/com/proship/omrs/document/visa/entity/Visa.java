package com.proship.omrs.document.visa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.document.base.entity.Document;
import com.proship.omrs.utils.util.Utils;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Visa extends Document{

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="visa_id_seq")
    @SequenceGenerator(
            name="visa_id_seq",
            sequenceName="visa_id_sequence"
    )
    private Long id;

    private Long uuid;

    private Long visaTypeId;

    private String  multiplicity;

    @Transient
    private VisaType visaType;

    @Transient
    private VisaDetail detail;

    @Transient
    private Date formDate;

    @Transient
    private String comment;

    @JsonIgnore
    private Long participantId;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "visa")
    @OrderBy("transactiontime")
    private List<VisaDetail> detailList;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "visa")
    @OrderBy("transactiontime")
    List<VisaFormDate> formDateList;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "visa")
    @OrderBy("transactiontime")
    List<VisaComment> commentList;

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

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public Long getVisa_type_id() {
        return visaTypeId;
    }

    public void setVisa_type_id(Long visaTypeId) {
        this.visaTypeId = visaTypeId;
    }

    public String getMultiplicity() {
        return multiplicity;
    }

    public void setMultiplicity(String multiplicity) {
        this.multiplicity = multiplicity;
    }

    public VisaType getVisaType() {

        this.visaType =VisaTypeMap.getVisaType(visaTypeId);

        return visaType;
    }

    public void setVisaType(VisaType visaType) {

        this.visaType = visaType;
    }

    public VisaDetail getDetail() {

        detail = Utils.getLastElementFromList(detailList);

        return detail;
    }

    public void setDetail(VisaDetail detail) {
        this.detail = detail;
    }

    public Date getFormDate() {

        formDate = Utils.getLastElementFromList(formDateList).getValue();

        return formDate;
    }

    public void setFormDate(Date formDate) {
        this.formDate = formDate;
    }

    public List<VisaDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<VisaDetail> detailList) {
        this.detailList = detailList;
    }

    public List<VisaFormDate> getFormDateList() {
        return formDateList;
    }

    public void setFormDateList(List<VisaFormDate> formDateList) {
        this.formDateList = formDateList;
    }

    public List<VisaComment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<VisaComment> commentList) {
        this.commentList = commentList;
    }

    public String getComment() {

        comment = Utils.getLastElementFromList(commentList).getText();

        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getVisaTypeId() {
        return visaTypeId;
    }

    public void setVisaTypeId(Long visaTypeId) {
        this.visaTypeId = visaTypeId;
    }
}
