package com.proship.omrs.document.medical.entity;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.proship.omrs.document.medical.repository.MedicalTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public class MedicalTypeMap implements InitializingBean{


    static final Logger logger = LoggerFactory.getLogger(MedicalTypeMap.class);
    private static BiMap<Long,MedicalType> medicalTypes;

    @Autowired
    MedicalTypeRepository repo;

    @Override
    public void afterPropertiesSet() throws Exception {

        logger.info("[medicalTypeMap][afterPropertiesSet]start");
        List<MedicalType> list = repo.findAll();

        medicalTypes = HashBiMap.create();

        for (MedicalType medicalType: list){
            medicalTypes.put(medicalType.getId(),medicalType);
        }

        logger.info("[medicalTypeMap][afterPropertiesSet]finish setting "+ medicalTypes.size()+" medicalType types. ");
    }
    public static Map getMedicalTypes(){
        return  medicalTypes;
    };

    public static MedicalType getMedicalType(Long id){
        return medicalTypes.get(id);
    }
}
