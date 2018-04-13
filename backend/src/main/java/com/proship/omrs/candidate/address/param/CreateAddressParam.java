package com.proship.omrs.candidate.address.param;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateAddressParam {

    @NotNull
    @Size(min=2, max=100)
    private String line1 ;


    private String line2 ;

    @NotNull
    @Size(min=2, max=30)
    private String city ;

    @NotNull
    @Size(min=2, max=30)
    private String region ;

    @NotNull
    @Size(min=2, max=30)
    private String postalCode ;

    @NotNull
    @Range(min = 0)
    private Long country;

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Long getCountry() {
        return country;
    }

    public void setCountry(Long country) {
        this.country = country;
    }
}
