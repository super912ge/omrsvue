package com.proship.omrs.base.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.user.entity.User;
import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class MainShardEntity extends BaseEntity {

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name="creatorId")
    @JsonIgnore
    private User creator;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date validstarttime;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date validendtime;

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Date getValidstarttime() {
        return validstarttime;
    }

    public void setValidstarttime(Date validstarttime) {
        this.validstarttime = validstarttime;
    }

    public Date getValidendtime() {
        return validendtime;
    }

    public void setValidendtime(Date validendtime) {
        this.validendtime = validendtime;
    }
}
