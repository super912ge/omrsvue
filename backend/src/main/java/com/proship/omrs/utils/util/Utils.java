package com.proship.omrs.utils.util;

import com.proship.omrs.base.entity.BaseEntity;
import com.proship.omrs.user.entity.User;
import com.proship.omrs.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public class Utils {


    public static <T extends BaseEntity> T getLastElementFromList(List<T> list){


        T element = null;
        if (list!=null && !list.isEmpty()){
            element = list.get(list.size()-1);
        }
        return element;
    }
}
