package com.proship.omrs.candidate.candidate.entity;

import com.proship.omrs.base.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "participant_gender_tts")
public class ParticipantGender extends BaseEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="participant_gender_tts_id_seq")
    @SequenceGenerator(
            name="participant_gender_tts_id_seq",
            sequenceName="participant_gender_tts_id_sequence"
    )

    private String value;


}
