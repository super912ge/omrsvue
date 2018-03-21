package com.proship.omrs.document.visa.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.document.base.entity.DocumentComment;

import javax.persistence.*;

@Table(name = "visa_comment_tts")
@Entity
public class VisaComment extends DocumentComment{

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="visa_comment_tts_id_seq")
    @SequenceGenerator(
            name="visa_comment_tts_id_seq",
            sequenceName="visa_comment_tts_id_sequence"
    )
    private Long id;

    @JsonIgnore
    @ManyToOne
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
