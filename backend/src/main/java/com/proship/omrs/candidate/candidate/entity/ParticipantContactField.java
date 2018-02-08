package com.proship.omrs.candidate.candidate.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.system.contact.entity.ContactFieldLabel;
import com.proship.omrs.system.contact.entity.ContactFieldType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class ParticipantContactField {
    @Id
    private Long id;

    @JsonIgnore
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date transactiontime ;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "creatorId")
//    @JsonSerialize(using = UserSerializer.class)
    @JsonIgnore
    private Long creatorId;

    private String value ;

    @ManyToOne
    private ContactFieldLabel contactFieldLabel;

    @ManyToOne
    private ContactFieldType contactFieldType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTransactiontime() {
        return transactiontime;
    }

    public void setTransactiontime(Date transactiontime) {
        this.transactiontime = transactiontime;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ContactFieldLabel getContactFieldLabel() {
        return contactFieldLabel;
    }

    public void setContactFieldLabel(ContactFieldLabel contactFieldLabel) {
        this.contactFieldLabel = contactFieldLabel;
    }

    public ContactFieldType getContactFieldType() {
        return contactFieldType;
    }

    public void setContactFieldType(ContactFieldType contactFieldType) {
        this.contactFieldType = contactFieldType;
    }
}
