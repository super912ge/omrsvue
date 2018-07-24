package com.proship.omrs.gig.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.contract.entity.ContractMainShard;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.List;
import java.util.stream.Collectors;

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
    private List<ContractMainShard> contracts;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chairRequirementTagId")
    private ChairRequirementTag chairRequirementTag;

    @OneToOne(mappedBy = "gig")
    private ChairLabelTts chair;

    @OneToMany(mappedBy = "gig", fetch = FetchType.EAGER)
    @OrderBy(value = "validendtime desc")
    @JsonIgnore
    private List<GigMainShard> shards;

    @Transient
    private GigMainShard currentShard;

    @JsonIgnore
    @OneToOne(mappedBy = "gig",fetch = FetchType.LAZY)
    private GigTerritoryShard territory;

    @OneToOne(mappedBy = "gig",fetch = FetchType.LAZY)
    private GigPeriodShard period;

    public List<GigMainShard> getShards() {
        return shards;
    }

    public void setShards(List<GigMainShard> shards) {
        this.shards = shards;
    }

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

    public List<ContractMainShard> getContracts() {
        return contracts;
    }

    public void setContracts(List<ContractMainShard> contracts) {
        this.contracts = contracts;
    }

    public GigMainShard getCurrentShard() {
        List<GigMainShard> currentValidShards = this.shards.stream()
                .filter( s->s.getValidendtime()
                        .after(new Timestamp(System.currentTimeMillis()))).collect(Collectors.toList());
        if (!currentValidShards.isEmpty())
        return currentValidShards.get(0);
        else return null;
    }


    public void setCurrentShard(GigMainShard shard) {
        this.currentShard = shard;
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
