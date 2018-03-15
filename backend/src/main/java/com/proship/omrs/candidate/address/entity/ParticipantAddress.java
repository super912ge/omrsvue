package com.proship.omrs.candidate.address.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.candidate.base.entity.BaseEntityCountry;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class ParticipantAddress extends BaseEntityCountry{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="participant_address_id_seq")
    @SequenceGenerator(
            name="participant_address_id_seq",
            sequenceName="participant_address_id_sequence"
    )
    private Long id;

    @JsonIgnore
    private Timestamp transactiontime;

    @JsonIgnore
    private Long creatorId;

    private String line1 ;

    private String line2 ;

    private String city ;

    private String region ;

    private String postalCode ;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getTransactiontime() {
        return transactiontime;
    }

    public void setTransactiontime(Timestamp transactiontime) {
        this.transactiontime = transactiontime;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

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

}
