package com.proship.omrs.candidate.contact.entity;

import com.proship.omrs.candidate.base.entity.BaseEntity;

import javax.persistence.*;

@Entity
public class ParticipantContactField extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="participant_contact_field_id_seq")
    @SequenceGenerator(
            name="participant_contact_field_id_seq",
            sequenceName="participant_contact_field_id_sequence"
    )
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

//    ParticipantContactField getEntity(){
//
//    }
}
