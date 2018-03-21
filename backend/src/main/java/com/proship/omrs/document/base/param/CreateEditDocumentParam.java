package com.proship.omrs.document.base.param;

import java.util.Date;

public class CreateEditDocumentParam {

    private Long id;

    private String comment;

    private Date issuingDate;

    private Date expiryDate;

    private Date formDate;

    private String issuingPost;

    private Boolean fitForDuty;

    private String multiplicity;

    private Long type;

    private Long country;

    private String number;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getIssuingDate() {
        return issuingDate;
    }

    public void setIssuingDate(Date issuingDate) {
        this.issuingDate = issuingDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getFormDate() {
        return formDate;
    }

    public void setFormDate(Date formDate) {
        this.formDate = formDate;
    }

    public String getIssuingPost() {
        return issuingPost;
    }

    public void setIssuingPost(String issuingPost) {
        this.issuingPost = issuingPost;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCountry() {
        return country;
    }

    public void setCountry(Long country) {
        this.country = country;
    }

    public Boolean getFitForDuty() {
        return fitForDuty;
    }

    public void setFitForDuty(Boolean fitForDuty) {
        this.fitForDuty = fitForDuty;
    }

    public String getMultiplicity() {
        return multiplicity;
    }

    public void setMultiplicity(String multiplicity) {
        this.multiplicity = multiplicity;
    }
}
