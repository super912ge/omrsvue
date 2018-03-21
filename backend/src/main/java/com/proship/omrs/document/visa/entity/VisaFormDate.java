package com.proship.omrs.document.visa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.document.base.entity.DocumentFormDate;
import javax.persistence.*;

@Entity
@Table(name = "visa_form_date_tts")
public class VisaFormDate extends DocumentFormDate{

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="visa_form_date_tts_id_seq")
    @SequenceGenerator(
            name="visa_form_date_tts_id_seq",
            sequenceName="visa_form_date_tts_id_sequence"
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
