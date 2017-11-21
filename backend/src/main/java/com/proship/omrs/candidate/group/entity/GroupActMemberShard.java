package com.proship.omrs.candidate.group.entity;

import com.proship.omrs.base.entity.MainShardEntity;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class GroupActMemberShard extends MainShardEntity{

    @Id
    private Long id;

    @ManyToOne
    private Act act ;
}
