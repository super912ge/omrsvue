package com.proship.omrs.document.seamansBook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.document.base.entity.Document;
import com.proship.omrs.system.country.entity.Country;
import com.proship.omrs.system.country.entity.CountryMap;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

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

    private Long countryId;

    @Transient
    private Country country;

    @Transient
    private Date formDate;

    @Transient
    private String comment;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "seamansBook")
    @Where(clause = "nexttransactiontime > now()")
    @JsonIgnore
    private SeamansBookDetail seamansBookDetail;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "seamansBook")
    @Where(clause = "nexttransactiontime > now()")
    @JsonIgnore
    private SeamansBookFormDate seamansBookFormDate;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "seamansBook")
    @Where(clause = "nexttransactiontime > now()")
    @JsonIgnore
    private SeamansBookComment seamansBookComment;

    @JsonIgnore
    private Long participantId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Country getCountry() {
        setCountry(CountryMap.getCountry(countryId));
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Date getFormDate() {
        setFormDate(seamansBookFormDate.getValue());
        return formDate;
    }

    public void setFormDate(Date formDate) {
        this.formDate = formDate;
    }

    public String getComment() {
        setComment(seamansBookComment.getText());
        return comment;
    }

    public SeamansBookDetail getSeamansBookDetail() {
        return seamansBookDetail;
    }

    public void setSeamansBookDetail(SeamansBookDetail seamansBookDetail) {
        this.seamansBookDetail = seamansBookDetail;
    }

    public SeamansBookFormDate getSeamansBookFormDate() {
        return seamansBookFormDate;
    }

    public void setSeamansBookFormDate(SeamansBookFormDate seamansBookFormDate) {
        this.seamansBookFormDate = seamansBookFormDate;
    }

    public SeamansBookComment getSeamansBookComment() {
        return seamansBookComment;
    }

    public void setSeamansBookComment(SeamansBookComment seamansBookComment) {
        this.seamansBookComment = seamansBookComment;
    }

    public void setComment(String comment) {

        this.comment = comment;
    }
}
