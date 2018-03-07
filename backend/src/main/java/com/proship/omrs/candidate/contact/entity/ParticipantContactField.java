package com.proship.omrs.candidate.contact.entity;

import com.proship.omrs.candidate.base.entity.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ParticipantContactField extends BaseEntity{

    @Id
    private Long id;

    private String value ;

    private Long contactFieldLabelId;

    private Long contactFieldTypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getContactFieldLabelId() {
        return contactFieldLabelId;
    }

    public void setContactFieldLabelId(Long contactFieldLabelId) {
        this.contactFieldLabelId = contactFieldLabelId;
    }

    public Long getContactFieldTypeId() {
        return contactFieldTypeId;
    }

    public void setContactFieldTypeId(Long contactFieldTypeId) {
        this.contactFieldTypeId = contactFieldTypeId;
    }
}
