package com.proship.omrs.document.passport.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.document.base.entity.DocumentDetail;

import javax.persistence.*;

@Entity
@Table(name = "passport_detail_tts")
public class PassportDetail extends DocumentDetail {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="passport_detail_tts_id_seq")
    @SequenceGenerator(
            name="passport_detail_tts_id_seq",
            sequenceName="passport_detail_tts_id_sequence"
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
