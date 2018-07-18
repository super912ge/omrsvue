package com.proship.omrs.msd.entity;

import com.proship.omrs.base.entity.MainShardEntity;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Where(clause = "nexttransactiontime>now()")
public class UserMsdBracketShard extends MainShardEntity {

    @Id
    private Long id;

    @OneToOne
    private MsdBracket msdBracket;

    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MsdBracket getMsdBracket() {
        return msdBracket;
    }

    public void setMsdBracket(MsdBracket msdBracket) {
        this.msdBracket = msdBracket;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
