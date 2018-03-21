package com.proship.omrs.document.visa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.document.base.entity.DocumentDetail;

import javax.persistence.*;

@Entity
@Table(name = "visa_detail_tts")
public class VisaDetail extends DocumentDetail {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="visa_detail_tts_id_seq")
    @SequenceGenerator(
            name="visa_detail_tts_id_seq",
            sequenceName="visa_detail_date_tts_id_sequence"
    )
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Visa visa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Visa getVisa() {
        return visa;
    }

    public void setVisa(Visa visa) {
        this.visa = visa;
    }
}
