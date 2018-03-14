package com.proship.omrs.candidate.info.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proship.omrs.candidate.base.entity.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ParticipantInfoTemporal extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="participant_info_temporal_id_seq")
    @SequenceGenerator(
            name="participant_info_temporal_id_seq",
            sequenceName="participant_info_temporal_id_sequence"
    )
    private Long id;


    private String participantinfotype ;

    private String participantinfovalue ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParticipantinfotype() {
        return participantinfotype;
    }

    public void setParticipantinfotype(String participantinfotype) {
        this.participantinfotype = participantinfotype;
    }

    public String getParticipantinfovalue() {
        return participantinfovalue;
    }

    public void setParticipantinfovalue(String participantinfovalue) {
        this.participantinfovalue = participantinfovalue;
    }
}
