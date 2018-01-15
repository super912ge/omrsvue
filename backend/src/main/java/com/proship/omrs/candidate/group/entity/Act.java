package com.proship.omrs.candidate.group.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.contract.entity.JobMainShard;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Act {

    @Id
    private Long id;
    @JsonIgnore
    private Integer specialtyType;
    @JsonIgnore
    private Long uuid;

    @OneToMany(mappedBy = "act")
    @OrderBy(value = "validendtime")
    private List<GroupActMainShard> groupActMainShards;

    @Transient
    private GroupActMainShard groupActMainShard;

    @OneToMany(mappedBy = "groupAct")
    private Set<JobMainShard> jobMainShards;

    @OneToMany
    @Where(clause = "groupActMemberShard.validendtime >current_date and " +
            "groupActMemberShard.nexttransactiontime > current_date")
    private Set<GroupActMemberShard> groupActMemberShards;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSpecialtyType() {
        return specialtyType;
    }

    public void setSpecialtyType(Integer specialtyType) {
        this.specialtyType = specialtyType;
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public GroupActMainShard getGroupActMainShard() {

        this.groupActMainShard = groupActMainShards.get(groupActMainShards.size()-1);
        return groupActMainShard;
    }

    public void setGroupActMainShard(GroupActMainShard groupActMainShard) {
        this.groupActMainShard = groupActMainShard;
    }

    public List<GroupActMainShard> getGroupActMainShards() {
        return groupActMainShards;
    }

    public void setGroupActMainShards(List<GroupActMainShard> groupActMainShards) {
        this.groupActMainShards = groupActMainShards;
    }

    public Set<JobMainShard> getJobMainShards() {
        return jobMainShards;
    }

    public void setJobMainShards(Set<JobMainShard> jobMainShards) {
        this.jobMainShards = jobMainShards;
    }

    public Set<GroupActMemberShard> getGroupActMemberShards() {
        return groupActMemberShards;
    }

    public void setGroupActMemberShards(Set<GroupActMemberShard> groupActMemberShards) {
        this.groupActMemberShards = groupActMemberShards;
    }
}
