package com.proship.omrs.document.certificate.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.document.base.entity.Document;
import com.proship.omrs.utils.util.Utils;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Certificate extends Document{
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="certificate_id_seq")
    @SequenceGenerator(
            name="certificate_id_seq",
            sequenceName="certificate_id_sequence"
    )
    private Long id;

    private Long uuid;

    private Long certificateTypeId;

    private Long countryId;

    private String number;

    @Transient
    private CertificateType certificateType;

    @Transient
    private CertificateDetail detail;

    @Transient
    private Date formDate;

    @Transient
    private String comment;

    @JsonIgnore
    private Long participantId;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "certificate")
    @OrderBy("transactiontime")
    private List<CertificateDetail> detailList;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "certificate")
    @OrderBy("transactiontime")
    List<CertificateFormDate> formDateList;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "certificate")
    @OrderBy("transactiontime")
    List<CertificateComment> commentList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public Long getCertificate_type_id() {
        return certificateTypeId;
    }

    public void setCertificate_type_id(Long certificateTypeId) {
        this.certificateTypeId = certificateTypeId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getCountryId() {
        return countryId;
    }

    public CertificateType getCertificateType() {

        this.certificateType = CertificateTypeMap.getCertificateType(certificateTypeId);

        return certificateType;
    }

    public void setCertificateType(CertificateType certificateType) {

        this.certificateType = certificateType;
    }

    public CertificateDetail getDetail() {

        detail = Utils.getLastElementFromList(detailList);

        return detail;
    }

    public void setDetail(CertificateDetail detail) {
        this.detail = detail;
    }

    public Date getFormDate() {

        formDate = Utils.getLastElementFromList(formDateList).getValue();

        return formDate;
    }

    public void setFormDate(Date formDate) {
        this.formDate = formDate;
    }

    public List<CertificateDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<CertificateDetail> detailList) {
        this.detailList = detailList;
    }

    public List<CertificateFormDate> getFormDateList() {
        return formDateList;
    }

    public void setFormDateList(List<CertificateFormDate> formDateList) {
        this.formDateList = formDateList;
    }

    public List<CertificateComment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CertificateComment> commentList) {
        this.commentList = commentList;
    }

    public String getComment() {

        comment = Utils.getLastElementFromList(commentList).getText();

        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getCertificateTypeId() {
        return certificateTypeId;
    }

    public void setCertificateTypeId(Long certificateTypeId) {
        this.certificateTypeId = certificateTypeId;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }
}
