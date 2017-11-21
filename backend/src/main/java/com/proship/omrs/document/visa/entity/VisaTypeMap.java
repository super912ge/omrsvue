package com.proship.omrs.document.visa.entity;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.proship.omrs.document.visa.repository.VisaTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public class VisaTypeMap implements InitializingBean{

    static final Logger logger = LoggerFactory.getLogger(VisaTypeMap.class);
    private static BiMap<Long,VisaType> visaTypes;

    @Autowired
    VisaTypeRepository repo;

    @Override
    public void afterPropertiesSet() throws Exception {

        logger.info("[VisaTypeMap][afterPropertiesSet]start");
        List<VisaType> list = repo.findAll();

        visaTypes = HashBiMap.create();

        for (VisaType visaType: list){
            visaTypes.put(visaType.getId(),visaType);
        }

        logger.info("[VisaTypeMap][afterPropertiesSet]finish setting "+ visaTypes.size()+" VisaType types. ");
    }
    public static Map getvisaTypes(){
        return  visaTypes;
    };

    public static VisaType getVisaType(Long id){
        return visaTypes.get(id);
    }
}
