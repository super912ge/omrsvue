package com.proship.omrs.candidate.contact.param;

import com.google.common.collect.HashBiMap;
import com.proship.omrs.candidate.contact.entity.ContactFieldLabel;
import com.proship.omrs.candidate.contact.repository.ContactFieldLabelRepository;
import com.proship.omrs.role.entity.RoleMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class ContactFieldLabelMap implements InitializingBean{
    static final Logger logger = LoggerFactory.getLogger(RoleMap.class);
    private static Map<Long,ContactFieldLabel> contactFieldLabelMap;

    @Autowired
    ContactFieldLabelRepository repo;

    @Override
    public void afterPropertiesSet() throws Exception {

        logger.info("[RoleMap][afterPropertiesSet]start");

        List<ContactFieldLabel> list = repo.findAll();

        contactFieldLabelMap = HashBiMap.create();

        for (ContactFieldLabel contactFieldLabel: list){
            contactFieldLabelMap.put(contactFieldLabel.getId(),contactFieldLabel);
        }

        logger.info("[ContactFieldLabelMap][afterPropertiesSet]finish setting "+ contactFieldLabelMap.size()+" countries. ");
    }
    public static Map getcontactFieldLabelMap(){
        return  contactFieldLabelMap;
    };

    public static ContactFieldLabel getcontactFieldLabel(Long id){
        return contactFieldLabelMap.get(id);
    }
}
