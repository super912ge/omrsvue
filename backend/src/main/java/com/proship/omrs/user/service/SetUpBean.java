package com.proship.omrs.user.service;

import com.proship.omrs.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;


public class SetUpBean {

    private UserService userService;


    public void setUpUser(){
        User user = new User();
        user.setName("yiwei_test2");
        user.setPassword("password");
        user.setEmail("yge@proship.com");
        user.setActive(true);
        user.setFull_name("Ms. yiwei");

        user.setIncentive_bracket_setting_id((long)2);
        userService.registerNewUser(user);

    }
}
