package com.proship.omrs.candidate.group.entity;


import com.proship.omrs.contract.entity.JobMainShard;
import org.hibernate.annotations.Where;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

@Entity
public class Act {

    private Long id;
    private Integer specialtyType;
    private Long uuid;

    @OneToOne
    @Where(clause = "groupActMainShard.validendtime >current_date and " +
            "groupActMainShard.nexttransactiontime > current_date")
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
        return groupActMainShard;
    }

    public void setGroupActMainShard(GroupActMainShard groupActMainShard) {
        this.groupActMainShard = groupActMainShard;
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
