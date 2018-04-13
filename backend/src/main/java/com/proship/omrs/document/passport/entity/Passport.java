package com.proship.omrs.document.passport.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.document.base.entity.Document;
import com.proship.omrs.system.country.entity.Country;
import com.proship.omrs.system.country.entity.CountryMap;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Passport extends Document{

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="passport_id_seq")
    @SequenceGenerator(
            name="passport_id_seq",
            sequenceName="passport_id_sequence"
    )
    private Long id;

    private Long countryId;

    @Transient
    private Country country;

    @Transient
    private Date formDate;

    @Transient
    private String comment;

    @JsonIgnore
    private Long participantId;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "passport")
    @Where(clause = "nexttransactiontime > now()")
    private PassportDetail passportDetail;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "passport")
    @Where(clause = "nexttransactiontime > now()")
    PassportFormDate passportFormDate;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "passport")
    @Where(clause = "nexttransactiontime > now()")
    @JsonIgnore
    PassportComment passportComment;

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

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Date getFormDate() {
        return formDate;
    }

    public void setFormDate(Date formDate) {
        this.setFormDate(this.passportFormDate.getValue());
        this.formDate = formDate;
    }

    public String getComment() {
        setComment(this.passportComment.getText());
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public PassportDetail getPassportDetail() {
        return passportDetail;
    }

    public void setPassportDetail(PassportDetail passportDetail) {
        this.passportDetail = passportDetail;
    }

    public PassportFormDate getPassportFormDate() {
        return passportFormDate;
    }

    public void setPassportFormDate(PassportFormDate passportFormDate) {
        this.passportFormDate = passportFormDate;
    }

    public PassportComment getPassportComment() {
        return passportComment;
    }

    public void setPassportComment(PassportComment passportComment) {
        this.passportComment = passportComment;
    }

    public Country getCountry() {

        setCountry(CountryMap.getCountry(countryId));
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
