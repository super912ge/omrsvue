package com.proship.omrs.document.medical.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.document.base.entity.DocumentComment;

import javax.persistence.*;

@Entity
@Table(name = "medical_comment_tts")
public class MedicalComment extends DocumentComment {


    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="medical_comment_id_seq")
    @SequenceGenerator(
            name="medical_comment_id_seq",
            sequenceName="medical_comment_tts_id_sequence"
    )
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Medical medical;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Medical getMedical() {
        return medical;
    }

    public void setMedical(Medical medical) {
        this.medical = medical;
    }
}
