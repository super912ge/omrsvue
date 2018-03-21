package com.proship.omrs.document.medical.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.document.base.entity.DocumentDetail;

import javax.persistence.*;

@Entity
@Table(name = "medical_detail_tts")
public class MedicalDetail extends DocumentDetail {


    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="medical_detail_tts_id_seq")
    @SequenceGenerator(
            name="medical_detail_tts_id_seq",
            sequenceName="medical_detail_tts_id_sequence"
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
