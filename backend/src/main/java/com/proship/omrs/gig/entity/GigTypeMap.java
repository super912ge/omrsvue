package com.proship.omrs.gig.entity;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.proship.omrs.gig.repository.BandTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class GigTypeMap implements InitializingBean{

    static final Logger logger = LoggerFactory.getLogger(GigTypeMap.class);
    private static BiMap<Long, BandType> bandTypes;

    @Autowired
    BandTypeRepository repo;

    @Override
    public void afterPropertiesSet() throws Exception {

        logger.info("[bandTypeMap][afterPropertiesSet]start");
        List<BandType> list = repo.findAll();

        bandTypes = HashBiMap.create();

        for (BandType bandType : list) {
            bandTypes.put(bandType.getId(), bandType);
        }

        logger.info("[bandTypeMap][afterPropertiesSet]finish setting " + bandTypes.size() + " bandType types.");
    }

    public static Map getBandTypes() {
        return bandTypes;
    }

    public static BandType getBandType(Long id) {
        return bandTypes.get(id);
    }
    
    
}
