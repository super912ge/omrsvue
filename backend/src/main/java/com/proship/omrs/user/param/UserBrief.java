package com.proship.omrs.user.param;

import com.proship.omrs.user.entity.User;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "System_user")
@Where(clause = "active = true and full_name is not null")
public class UserBrief {
    public UserBrief(Long id, String name, Boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public UserBrief(){

    }
    public UserBrief(User user){
        this.id = user.getId();
        this.name = user.getFullName();
    }

    @Id
    private Long id;

    @Column(name = "fullName")
    private String name;

    private Boolean active;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
