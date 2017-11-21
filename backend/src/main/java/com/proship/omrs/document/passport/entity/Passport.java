package com.proship.omrs.document.passport.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.document.base.entity.Document;
import com.proship.omrs.utils.util.Utils;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity

public class Passport extends Document{

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="passport_id_seq")
    @SequenceGenerator(
            name="passport_id_seq",
            sequenceName="passport_id_sequence"
    )
    private Long id;

    private String number ;

    private Long uuid;

    private Long countryId;

    @Transient
    private PassportDetail detail;

    @Transient
    private Date formDate;

    @Transient
    private String comment;

    @JsonIgnore
    private Long participantId;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "passport")
    @OrderBy("transactiontime")
    private List<PassportDetail> detailList;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "passport")
    @OrderBy("transactiontime")
    List<PassportFormDate> formDateList;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "passport")
    @OrderBy("transactiontime")
    List<PassportComment> commentList;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public PassportDetail getDetail() {

        detail = Utils.getLastElementFromList(detailList);

        return detail;
    }

    public void setDetail(PassportDetail detail) {
        this.detail = detail;
    }

    public Date getFormDate() {

        formDate = Utils.getLastElementFromList(formDateList).getValue();

        return formDate;
    }

    public void setFormDate(Date formDate) {
        this.formDate = formDate;
    }

    public List<PassportDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<PassportDetail> detailList) {
        this.detailList = detailList;
    }

    public List<PassportFormDate> getFormDateList() {
        return formDateList;
    }

    public void setFormDateList(List<PassportFormDate> formDateList) {
        this.formDateList = formDateList;
    }

    public List<PassportComment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<PassportComment> commentList) {
        this.commentList = commentList;
    }

    public String getComment() {

        comment = Utils.getLastElementFromList(commentList).getText();

        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
