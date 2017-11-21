package com.proship.omrs.base.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.proship.omrs.jsonviews.UserSerializer;
import com.proship.omrs.user.entity.User;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@MappedSuperclass
public class MainShardEntity extends BaseEntity {

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="creator_id")
    @JsonSerialize(using = UserSerializer.class)
    User creator;

    @JsonFormat(pattern="yyyy-MM-dd")
    Timestamp validstarttime;
    @JsonFormat(pattern="yyyy-MM-dd")
    Timestamp validendtime;

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Timestamp getValidstarttime() {
        return validstarttime;
    }

    public void setValidstarttime(Timestamp validstarttime) {
        this.validstarttime = validstarttime;
    }

    public Timestamp getValidendtime() {
        return validendtime;
    }

    public void setValidendtime(Timestamp validendtime) {
        this.validendtime = validendtime;
    }
}
