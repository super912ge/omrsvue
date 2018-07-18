package com.proship.omrs.msd.entity;


import com.proship.omrs.base.entity.BaseEntityWithCreator;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Where(clause = "nexttransactiontime>now()")
public class MsdBracketTts extends BaseEntityWithCreator{

    @Id
    private Long id;

    private Long bracketId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBracketId() {
        return bracketId;
    }

    public void setBracketId(Long bracketId) {
        this.bracketId = bracketId;
    }
}
