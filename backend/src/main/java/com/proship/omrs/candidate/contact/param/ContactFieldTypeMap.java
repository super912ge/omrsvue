package com.proship.omrs.candidate.contact.param;

import com.google.common.collect.HashBiMap;
import com.proship.omrs.candidate.contact.entity.ContactFieldType;
import com.proship.omrs.candidate.contact.repository.ContactFieldTypeRepository;
import com.proship.omrs.role.entity.RoleMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class ContactFieldTypeMap implements InitializingBean{
    static final Logger logger = LoggerFactory.getLogger(RoleMap.class);
    private static Map<Long,ContactFieldType> contactFieldTypeMap;

    @Autowired
    ContactFieldTypeRepository repo;

    @Override
    public void afterPropertiesSet() throws Exception {

        logger.info("[RoleMap][afterPropertiesSet]start");

        List<ContactFieldType> list = repo.findAll();

        contactFieldTypeMap = HashBiMap.create();

        for (ContactFieldType contactFieldType: list){
            contactFieldTypeMap.put(contactFieldType.getId(),contactFieldType);
        }

        logger.info("[ContactFieldTypeMap][afterPropertiesSet]finish setting "+ contactFieldTypeMap.size()+" countries. ");
    }
    public static Map getcontactFieldTypeMap(){
        return  contactFieldTypeMap;
    };

    public static ContactFieldType getcontactFieldType(Long id){
        return contactFieldTypeMap.get(id);
    }
    
}
