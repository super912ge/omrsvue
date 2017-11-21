package com.proship.omrs.user.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


public class CustomUser extends User{

    private Long userId;

    public CustomUser(Long userId,String username, String password, boolean enabled,
                 boolean accountNonExpired, boolean credentialsNonExpired,
                 boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities){
        super(username,password,enabled,accountNonExpired,credentialsNonExpired,accountNonLocked,authorities);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
