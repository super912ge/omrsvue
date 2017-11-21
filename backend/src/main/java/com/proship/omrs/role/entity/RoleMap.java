package com.proship.omrs.role.entity;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import com.proship.omrs.role.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class RoleMap implements InitializingBean{

    static final Logger logger = LoggerFactory.getLogger(RoleMap.class);
    private static BiMap<Long,Role> roleTypes;

    @Autowired
    RoleRepository repo;

    @Override
    public void afterPropertiesSet() throws Exception {

        logger.info("[RoleMap][afterPropertiesSet]start");
        List<Role> list = repo.findAll();

        roleTypes = HashBiMap.create();

        for (Role role: list){
            roleTypes.put(role.getId(),role);
        }

        logger.info("[RoleMap][afterPropertiesSet]finish setting "+ roleTypes.size()+" role types. ");
    }
    public static Map getRoleTypes(){
        return  roleTypes;
    };

    public static Role getRoleType(Long id){
        return roleTypes.get(id);
    }

    //public static Long getRoleId(Role role){return roleTypes.inverse().get(role);}
}
