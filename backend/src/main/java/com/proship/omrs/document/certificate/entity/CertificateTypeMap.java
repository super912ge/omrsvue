package com.proship.omrs.document.certificate.entity;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.proship.omrs.document.certificate.repository.CertificateTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public class CertificateTypeMap implements InitializingBean{


    static final Logger logger = LoggerFactory.getLogger(CertificateTypeMap.class);
    private static BiMap<Long,CertificateType> certificateTypes;

    @Autowired
    CertificateTypeRepository repo;

    @Override
    public void afterPropertiesSet() throws Exception {

        logger.info("[certificateTypeMap][afterPropertiesSet]start.");
        List<CertificateType> list = repo.findAll();

        certificateTypes = HashBiMap.create();

        for (CertificateType certificateType : list){
            certificateTypes.put(certificateType.getId(), certificateType);
        }

        logger.info("[certificateTypeMap][afterPropertiesSet]finish setting "+ certificateTypes.size()+" certificateType types. ");
    }
    public static Map getCertificateTypes(){
        return  certificateTypes;
    };

    public static CertificateType getCertificateType(Long id){
        return certificateTypes.get(id);
    }
}
