package com.proship.omrs.candidate.group.entity;

import com.proship.omrs.candidate.candidate.entity.ParticipantAct;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class GroupActMembers {


    @OneToOne
    @JoinColumn(name = "memberShardId")
    private GroupActMemberShard groupActMemberShard;

    @OneToOne(mappedBy = "groupActMembers")
    private ParticipantAct participantAct ;

    public GroupActMemberShard getGroupActMemberShard() {
        return groupActMemberShard;
    }

    public void setGroupActMemberShard(GroupActMemberShard groupActMemberShard) {
        this.groupActMemberShard = groupActMemberShard;
    }

    public ParticipantAct getParticipantAct() {
        return participantAct;
    }

    public void setParticipantAct(ParticipantAct participantAct) {
        this.participantAct = participantAct;
    }
}
