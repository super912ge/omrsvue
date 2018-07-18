package com.proship.omrs.contract.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="job_id_seq")
    @SequenceGenerator(
            name="job_id_seq",
            sequenceName="job_id_sequence"
    )
    private Long id ;

    private Integer specialtyType ;

    private Long uuid;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "job_contracts",
            joinColumns = {@JoinColumn(name="job_id")},
            inverseJoinColumns = {@JoinColumn(name="contract_id")})
    private List<Contract> contracts;

    @OneToMany(mappedBy = "job")
    @Where(clause = "jobMainShards.nexttransactiontime > now()")
    private Set<JobMainShard> jobMainShards;

    @Transient
    private JobMainShard shard;

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

    public Set<JobMainShard> getJobMainShards() {
        return jobMainShards;
    }

    public void setJobMainShards(Set<JobMainShard> jobMainShard) {
        this.jobMainShards = jobMainShard;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public JobMainShard getShard(){
        Predicate<JobMainShard> p1 = e -> e.getValidendtime().before(new Date());
        Predicate<JobMainShard> p2 = e -> e.getValidstarttime().after(new Date());
        this.shard = jobMainShards.stream().filter(p1).filter(p2).findFirst().orElse(null);
        return this.shard;
    }

    public void setShard(JobMainShard shard) {
        this.shard = shard;
    }
}
