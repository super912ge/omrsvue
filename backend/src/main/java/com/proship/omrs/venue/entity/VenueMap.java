package com.proship.omrs.venue.entity;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.proship.omrs.venue.repository.VenueMainShardRepository;
import com.proship.omrs.venue.repository.VenueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class VenueMap implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(VenueMap.class);
    private static BiMap<Long, Venue> venueMap;

    @Autowired
    private VenueRepository repo;

    @Override
    public void afterPropertiesSet() throws Exception {

        logger.info("[VenueMap][afterPropertiesSet]start");
        List<Venue> list = repo.findAll();

        venueMap = HashBiMap.create();

        for (Venue venue : list) {
            venueMap.put(venue.getId(), venue);
        }

        logger.info("[VenueMap][afterPropertiesSet]finish setting " + venueMap.size() + " venues.");
    }

    public static BiMap<Long, Venue> getVenueMap() {
        return venueMap;
    }


    public static Venue getVenue(Long id) {
        return venueMap.get(id);
    }
}
