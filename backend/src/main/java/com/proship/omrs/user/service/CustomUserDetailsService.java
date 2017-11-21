package com.proship.omrs.user.service;

import com.proship.omrs.role.entity.Role;
import com.proship.omrs.user.entity.CustomUser;
import com.proship.omrs.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.proship.omrs.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{

    static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);


    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(@Lazy UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Transactional
    public UserDetails loadUserByUsername(String username){
        User user = userRepository.findByName(username);

        if(user==null){
            logger.info("User not found");
            throw new UsernameNotFoundException("username not found");
        }
        logger.info("User:{ }"+user.getName());

          //  sessionInfo.setUser(user);
            return new CustomUser(user.getId(),
                    user.getName(),user.getPassword(),
                    true,true,true,true,
                    getGrantedAuthorities(user));
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role userRole: user.getRoleList()){


            logger.info("UserRole: { }"+userRole);
            authorities.add(new SimpleGrantedAuthority(userRole.getName()));

        }
        logger.info("authorities: { }"+authorities);
        return authorities;
    }


}
