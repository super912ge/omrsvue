package com.proship.omrs.document.seamanBook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.document.base.entity.Document;
import com.proship.omrs.utils.util.Utils;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "seamans_book")
public class SeamansBook extends Document{

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seamans_book_id_seq")
    @SequenceGenerator(
            name="seamans_book_id_seq",
            sequenceName="seamans_book_id_sequence"
    )

    private Long id;

    private Long uuid;

    private String number;

    private Long countryId;

    @Transient
    private SeamansBookDetail detail;

    @Transient
    private Date formDate;

    @Transient
    private String comment;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "seamansBook")
    @OrderBy("transactiontime")
    private List<SeamansBookDetail> detailList;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "seamansBook")
    @OrderBy("transactiontime")
    List<SeamansBookFormDate> formDateList;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "seamansBook")
    @OrderBy("transactiontime")
    List<SeamansBookComment> commentList;

    @JsonIgnore
    private Long participantId;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }


    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public SeamansBookDetail getDetail() {

        detail = Utils.getLastElementFromList(detailList);

        return detail;
    }

    public void setDetail(SeamansBookDetail detail) {
        this.detail = detail;
    }

    public Date getFormDate() {

        formDate = Utils.getLastElementFromList(formDateList).getValue();

        return formDate;
    }

    public void setFormDate(Date formDate) {
        this.formDate = formDate;
    }

    public List<SeamansBookDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<SeamansBookDetail> detailList) {
        this.detailList = detailList;
    }

    public List<SeamansBookFormDate> getFormDateList() {
        return formDateList;
    }

    public void setFormDateList(List<SeamansBookFormDate> formDateList) {
        this.formDateList = formDateList;
    }

    public List<SeamansBookComment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<SeamansBookComment> commentList) {
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
