package com.proship.omrs.document.certificate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.document.base.entity.DocumentComment;

import javax.persistence.*;

@Entity
@Table(name = "certificate_comment_tts")
public class CertificateComment extends DocumentComment {


    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="certificate_comment_tts_id_seq")
    @SequenceGenerator(
            name="certificate_comment_tts_id_seq",
            sequenceName="certificate_comment_tts_id_sequence"
    )
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @ManyToOne
    @JsonIgnore
    Certificate certificate;

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

}
