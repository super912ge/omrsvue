package com.proship.omrs.chair;

import com.proship.omrs.base.entity.BaseEntity;
import com.proship.omrs.evaluation.entity.EvalTagType;
import com.proship.omrs.gig.entity.Gig;
import com.proship.omrs.user.entity.User;

import javax.persistence.*;
import java.util.List;

@Entity
public class ChairRequirementTag extends BaseEntity{

    @Id
    private Long id;

    private Long uuid;

    private Long evalTagTypeId;

//    @OneToOne(mappedBy = "chairRequirementTag")
//    private Gig gig;

    @ManyToOne
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
}
