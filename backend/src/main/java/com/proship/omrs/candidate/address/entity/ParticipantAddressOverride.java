package com.proship.omrs.candidate.address.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.candidate.participant.entity.Participant;
import com.proship.omrs.system.country.entity.CountryMap;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

public class ParticipantAddressOverride {

    @Id
    private Long id;

    @JsonIgnore
    private Long destroyerId;

    @JsonIgnore
    private Timestamp nexttransactiontime;

    @ManyToOne
    @JoinColumn(name = "id",insertable = false,updatable = false)
    private ParticipantAddress participantAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Participant participant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDestroyerId() {
        return destroyerId;
    }

    public void setDestroyerId(Long destroyerId) {
        this.destroyerId = destroyerId;
    }

    public Timestamp getNexttransactiontime() {
        return nexttransactiontime;
    }

    public void setNexttransactiontime(Timestamp nexttransactiontime) {
        this.nexttransactiontime = nexttransactiontime;
    }

    public ParticipantAddress getParticipantAddress() {
        return participantAddress;
    }

    public void setParticipantAddress(ParticipantAddress participantAddress) {
        this.participantAddress = participantAddress;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public String getAddress(){
        String line;
        if (participantAddress.getLine2()!=null&&"".equals(participantAddress.getLine2().trim())) {

            line = this.participantAddress.getLine1() + ", " + participantAddress.getLine2() + ", ";

        }else line = this.participantAddress.getLine1()+ ", ";

        return line+participantAddress.getCity()+", "+participantAddress.getRegion()+", "+
                CountryMap.getCountry(this.participantAddress.getCountryId()).getName()+", "
                +this.participantAddress.getPostalCode();

    }
}
