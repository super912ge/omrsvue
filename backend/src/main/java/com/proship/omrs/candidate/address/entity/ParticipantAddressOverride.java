package com.proship.omrs.candidate.address.entity;

import com.proship.omrs.candidate.base.entity.BaseOverrideEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ParticipantAddressOverride extends BaseOverrideEntity{

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id",insertable = false,updatable = false)
    private ParticipantAddress participantAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ParticipantAddress getParticipantAddress() {
        return participantAddress;
    }

    public void setParticipantAddress(ParticipantAddress participantAddress) {
        this.participantAddress = participantAddress;
    }


    public String getAddress(){
        String line;
        if (participantAddress.getLine2()!=null&&!"".equals(participantAddress.getLine2().trim())) {

            line = this.participantAddress.getLine1() + ", " + participantAddress.getLine2() + ", ";

        }else line = this.participantAddress.getLine1()+ ", ";

        line = line + participantAddress.getCity() + ", ";

        if(participantAddress.getRegion()!=null&&!"".equals(participantAddress.getRegion().trim()))
            line = line + participantAddress.getRegion() + ", ";


        return line + participantAddress.getCountry().getName() + ", "

                +this.participantAddress.getPostalCode();

    }
}
