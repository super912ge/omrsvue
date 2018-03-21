package com.proship.omrs.document.passport.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.document.base.entity.DocumentFormDate;

import javax.persistence.*;

@Entity
@Table(name = "passport_form_date_tts")
public class PassportFormDate extends DocumentFormDate{

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="passport_form_date_tts_id_seq")
    @SequenceGenerator(
            name="passport_form_date_tts_id_seq",
            sequenceName="passport_form_date_tts_id_sequence"
    )
    private Long id;


    @ManyToOne
    @JsonIgnore
    private Passport passport;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }


}
