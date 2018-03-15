package com.proship.omrs.document.base.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proship.omrs.document.base.entity.Document;
import com.proship.omrs.document.certificate.entity.Certificate;
import com.proship.omrs.document.medical.entity.Medical;
import com.proship.omrs.document.medical.entity.MedicalType;
import com.proship.omrs.document.passport.entity.Passport;
import com.proship.omrs.document.seamanBook.entity.SeamansBook;
import com.proship.omrs.document.visa.entity.Visa;
import com.proship.omrs.system.country.entity.CountryMap;
import java.util.Date;

public class DocumentBrief {


    public DocumentBrief(Visa visa){

        this.id = visa.getId();

        String[] strs = visa.getVisaType().getName().split("-");

        this.name = strs[1].trim();

        this.country = strs[0].trim();

        this.expiryDate = visa.getDetail().getExpiryDate();
    }
    public DocumentBrief(Passport passport){

        this.id = passport.getId();

        this.name = "PASSPORT";

        this.country = CountryMap.getCountry(passport.getCountryId()).getName();

        this.expiryDate = passport.getDetail().getExpiryDate();
    }

    public DocumentBrief(SeamansBook seamansBook){

        this.id = seamansBook.getId();

        this.expiryDate = seamansBook.getDetail().getExpiryDate();

        this.country = CountryMap.getCountry(seamansBook.getCountryId()).getName();

        this.name = "SEAMAN'S BOOK";
    }

    public DocumentBrief(Certificate certificate){

        this.id = certificate.getId();

        this.name = certificate.getCertificateType().getName().toUpperCase();

        this.country = CountryMap.getCountry(certificate.getCountryId()).getName();

        this.expiryDate = certificate.getDetail().getExpiryDate();
    }

    public DocumentBrief(Medical medical){

        this.id = medical.getId();

        String name = medical.getMedicalType().getName();

        String[] arr = name.split("-");

        this.name = arr[1].trim();

        this.country = arr[0].trim();

        this.expiryDate = medical.getDetail().getExpiryDate();
    }

    private Long id;

    private String name;

    private String country;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expiryDate;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
