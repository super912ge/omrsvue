package com.proship.omrs.candidate.residency.entity;


import com.proship.omrs.candidate.base.entity.BaseEntityCountry;

import javax.persistence.*;

@Entity

public class ParticipantResidency extends BaseEntityCountry {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="participant_residency_id_seq")
    @SequenceGenerator(
            name="participant_residency_id_seq",
            sequenceName="participant_residency_id_sequence"
    )
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
