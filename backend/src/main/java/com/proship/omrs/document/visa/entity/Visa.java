package com.proship.omrs.document.visa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.document.base.entity.Document;
import com.proship.omrs.utils.util.Utils;
import org.hibernate.annotations.Where;

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

    private Long visaTypeId;

    private String  multiplicity;

    @Transient
    private String type;

    @Transient
    private Date formDate;

    @Transient
    private String comment;

    @JsonIgnore
    private Long participantId;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "visa")
    @Where(clause = "nexttransactiontime>current_date")
    @JsonIgnore
    private  VisaDetail  visaDetail;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "visa")
    @Where(clause = "nexttransactiontime>current_date")
    @JsonIgnore
     VisaFormDate  visaFormDate;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "visa")
    @Where(clause = "nexttransactiontime >current_date")
    @JsonIgnore
     VisaComment visaComment;

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

    public String getMultiplicity() {
        return multiplicity;
    }

    public void setMultiplicity(String multiplicity) {
        this.multiplicity = multiplicity;
    }

    public Date getFormDate() {

        setFormDate(visaFormDate.getValue());

        return formDate;
    }

    public void setFormDate(Date formDate) {
        this.formDate = formDate;
    }

    public String getType() {

        setType(VisaTypeMap.getVisaType(visaTypeId).getName());
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        setComment(visaComment.getText());
        return comment;
    }

    public VisaDetail getVisaDetail() {
        return visaDetail;
    }

    public void setVisaDetail(VisaDetail visaDetail) {
        this.visaDetail = visaDetail;
    }

    public VisaFormDate getVisaFormDate() {
        return visaFormDate;
    }

    public void setVisaFormDate(VisaFormDate visaFormDate) {
        this.visaFormDate = visaFormDate;
    }

    public VisaComment getVisaComment() {
        return visaComment;
    }

    public void setVisaComment(VisaComment visaComment) {
        this.visaComment = visaComment;
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
