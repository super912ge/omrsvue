package com.proship.omrs.document.certificate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.document.base.entity.Document;
import com.proship.omrs.system.country.entity.Country;
import com.proship.omrs.system.country.entity.CountryMap;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import java.util.Date;


@Entity
public class Certificate extends Document{
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="certificate_id_seq")
    @SequenceGenerator(
            name="certificate_id_seq",
            sequenceName="certificate_id_sequence"
    )
    private Long id;
    
    private Long certificateTypeId;
    
    @Transient
    private String type;

    private Long countryId;

    @Transient
    private Country country;
    
    @Transient
    private CertificateType certificateType;

    @Transient
    private Date formDate;

    @Transient
    private String comment;

    @JsonIgnore
    private Long participantId;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "certificate")
    @JsonIgnore
    @Where(clause = "nexttransactiontime>now()")
    private  CertificateDetail certificateDetail ;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "certificate")
    @JsonIgnore
    @Where(clause = "nexttransactiontime>now()")
    private CertificateFormDate certificateFormDate ;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "certificate")
    @JsonIgnore
    @Where(clause = "nexttransactiontime>now()")
    private CertificateComment certificateComment ;

    public Country getCountry() {
        setCountry(CountryMap.getCountry(countryId));
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
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

    public Date getFormDate() {

        setFormDate(certificateFormDate.getValue());

        return formDate;
    }

    public void setFormDate(Date formDate) {
        this.formDate = formDate;
    }

    public String getType() {
        setType(CertificateTypeMap.getCertificateType(certificateTypeId).getName());
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        setComment(this.certificateComment.getText());
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public CertificateDetail getCertificateDetail() {
        return certificateDetail;
    }

    public void setCertificateDetail(CertificateDetail certificateDetail) {
        this.certificateDetail = certificateDetail;
    }

    public CertificateFormDate getCertificateFormDate() {
        return certificateFormDate;
    }

    public void setCertificateFormDate(CertificateFormDate certificateFormDate) {
        this.certificateFormDate = certificateFormDate;
    }

    public CertificateComment getCertificateComment() {
        return certificateComment;
    }

    public void setCertificateComment(CertificateComment certificateComment) {
        this.certificateComment = certificateComment;
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
