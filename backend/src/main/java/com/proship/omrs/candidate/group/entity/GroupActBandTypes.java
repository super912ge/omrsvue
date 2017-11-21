package com.proship.omrs.candidate.group.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GroupActBandTypes {

    @Id
    private Long groupactMainShardId ;

    private Long bandtypeId ;

    public Long getGroupactMainShardId() {
        return groupactMainShardId;
    }

    public void setGroupactMainShardId(Long groupactMainShardId) {
        this.groupactMainShardId = groupactMainShardId;
    }

    public Long getBandtypeId() {
        return bandtypeId;
    }

    public void setBandtypeId(Long bandtypeId) {
        this.bandtypeId = bandtypeId;
    }
}
