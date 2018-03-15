package com.proship.omrs.candidate.citizenship.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.candidate.base.entity.BaseEntity;
import com.proship.omrs.candidate.base.entity.BaseEntityCountry;
import com.proship.omrs.system.country.entity.Country;
import com.proship.omrs.system.country.entity.CountryMap;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class ParticipantCitizenship extends BaseEntityCountry{


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="participant_citizenship_id_seq")
    @SequenceGenerator(
            name="participant_citizenship_id_seq",
            sequenceName="participant_citizenship_id_sequence"
    )
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
