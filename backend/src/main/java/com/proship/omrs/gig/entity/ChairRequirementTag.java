package com.proship.omrs.gig.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.base.entity.BaseEntity;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Where(clause = "nexttransactiontime> now()")
public class ChairRequirementTag extends BaseEntity{

    @Id
    private Long id;

    @JsonIgnore
    private Long uuid;


    private Long evalTagTypeId;

//    @OneToOne(mappedBy = "chairRequirementTag")
//    private Gig gig;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private ChairRequirementTag parent;

    @OneToMany(mappedBy = "parent")
    private List<ChairRequirementTag> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public Long getEvalTagTypeId() {
        return evalTagTypeId;
    }

    public void setEvalTagTypeId(Long evalTagTypeId) {
        this.evalTagTypeId = evalTagTypeId;
    }

    public ChairRequirementTag getParent() {
        return parent;
    }

    public void setParent(ChairRequirementTag parent) {
        this.parent = parent;
    }

    public List<ChairRequirementTag> getChildren() {
        return children;
    }

    public void setChildren(List<ChairRequirementTag> children) {
        this.children = children;
    }
}
