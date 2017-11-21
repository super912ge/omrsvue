package com.proship.omrs.gig.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.chair.ChairRequirementTag;
import com.proship.omrs.contract.entity.Contract;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Gig {

    @Id
    private Long id;

    private Integer specialtyType;

    @ManyToOne
    private Gig parent;

    @OneToMany(mappedBy = "parent")
    private List<Gig> children;

    private Long uuid;

    @OneToMany(mappedBy = "gig", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Contract> contracts;

    @OneToOne
    @JoinColumn(name = "chairRequirementId")
    private ChairRequirementTag chairRequirementTag;

    @OneToOne(mappedBy = "gig")
    @Where(clause = "nexttransactiontime > current_date")
    private GigMainShard shard;

    @OneToOne(mappedBy = "gig")
    @Where(clause = "nexttransactiontime > current_date")
    private GigTerritoryShard territory;

    @OneToOne(mappedBy = "gig")
    @Where(clause = "nexttransactiontime > current_date")
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
}
