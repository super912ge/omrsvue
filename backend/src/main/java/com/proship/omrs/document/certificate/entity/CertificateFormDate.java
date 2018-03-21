package com.proship.omrs.document.certificate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.document.base.entity.DocumentFormDate;

import javax.persistence.*;

@Entity
@Table(name = "certificate_form_date_tts")
public class CertificateFormDate extends DocumentFormDate {


    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="certificate_form_date_tts_id_seq")
    @SequenceGenerator(
            name="certificate_form_date_tts_id_seq",
            sequenceName="certificate_form_date_tts_id_sequence"
    )
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Certificate certificate;


    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
