package com.proship.omrs.candidate.availibility.entity;

import com.proship.omrs.gig.repository.AvailabilityLevelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AvailabilityLevelMap implements InitializingBean{


    static final Logger logger = LoggerFactory.getLogger(AvailabilityLevelMap.class);
    private static Map<Long, AvailabilityLevel> availabilityLevels;

    @Autowired
    AvailabilityLevelRepository repo;

    @Override
    public void afterPropertiesSet() throws Exception {

        logger.info("[availabilityLevelMap][afterPropertiesSet]start");
        List<AvailabilityLevel> list = repo.findAll();

        availabilityLevels = new HashMap<>();

        for (AvailabilityLevel availabilityLevel : list) {
            availabilityLevels.put(availabilityLevel.getId(), availabilityLevel);
        }

        logger.info("[availabilityLevelMap][afterPropertiesSet]finish setting " + availabilityLevels.size() + " availabilityLevel types.");
    }

    public static Map getAvailabilityLevels() {
        return availabilityLevels;
    }

    public static AvailabilityLevel getAvailabilityLevel(Long id) {
        return availabilityLevels.get(id);
    }


}
