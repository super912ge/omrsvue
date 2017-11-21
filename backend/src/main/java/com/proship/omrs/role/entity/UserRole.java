package com.proship.omrs.role.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_role")
@IdClass(UserRoleId.class)
public class UserRole implements Serializable{




    @Id
    private Long userId;

    @Id
    private Long roleId;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId; }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    protected  UserRole(){};

    public UserRole( Long userId,Long roleId) {

        this.setUserId(userId);
        this.roleId = roleId;
    }

}
