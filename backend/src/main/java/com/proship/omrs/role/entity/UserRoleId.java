package com.proship.omrs.role.entity;

import java.io.Serializable;

public class UserRoleId implements Serializable{


    private Long userId;

    private Long roleId;



    protected UserRoleId(){};
    public UserRoleId(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}
