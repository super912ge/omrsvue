package com.proship.omrs.gig.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.chair.ChairRequirementTag;
import com.proship.omrs.contract.entity.ContractMainShard;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.jpa.repository.Query;

import javax.jdo.annotations.Value;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Gig {

    @Id
    private Long id;

    @JsonIgnore
    private Integer specialtyType;

    @ManyToOne
    @JoinColumn(name = "parent_gig_id")
    @JsonIgnore
    private Gig parentGig;

    @OneToMany(mappedBy = "parentGig")
    private List<Gig> children;

    @JsonIgnore
    private Long uuid;

    @OneToMany(mappedBy = "gig", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<ContractMainShard> contracts;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "chairRequirementTagId")
    private ChairRequirementTag chairRequirementTag;

    @OneToOne(mappedBy = "gig")
    private ChairLabelTts chair;

    @OneToMany(mappedBy = "gig",fetch = FetchType.LAZY)
    @OrderBy(value = "validendtime desc")
    @JsonIgnore
    private List<GigMainShard> shards;

    @Transient
    private GigMainShard shard;

    @JsonIgnore
    @OneToOne(mappedBy = "gig",fetch = FetchType.LAZY)
    private GigTerritoryShard territory;

    @OneToOne(mappedBy = "gig",fetch = FetchType.LAZY)
    private GigPeriodShard period;

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

    public ChairRequirementTag getChairRequirementTag() {
        return chairRequirementTag;
    }

    public void setChairRequirementTag(ChairRequirementTag chairRequirementTag) {
        this.chairRequirementTag = chairRequirementTag;
    }

    public Gig getParentGig() {
        return parentGig;
    }

    public void setParentGig(Gig parentGig) {
        this.parentGig = parentGig;
    }

    public List<Gig> getChildren() {
        return children;
    }

    public void setChildren(List<Gig> children) {
        this.children = children;
    }

    public Set<ContractMainShard> getContracts() {
        return contracts;
    }

    public void setContracts(Set<ContractMainShard> contracts) {
        this.contracts = contracts;
    }

    public GigMainShard getShard() {
        this.shard = this.shards.get(0);
        return shard;
    }

    public void setShard(GigMainShard shard) {
        this.shard = shard;
    }

    public GigTerritoryShard getTerritory() {
        return territory;
    }

    public void setTerritory(GigTerritoryShard territory) {
        this.territory = territory;
    }

    public GigPeriodShard getPeriod() {
        return period;
    }

    public void setPeriod(GigPeriodShard period) {
        this.period = period;
    }

    public ChairLabelTts getChair() {
        return chair;
    }

    public void setChair(ChairLabelTts chair) {
        this.chair = chair;
    }
}
