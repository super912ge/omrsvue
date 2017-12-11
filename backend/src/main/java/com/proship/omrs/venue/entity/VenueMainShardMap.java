package com.proship.omrs.venue.entity;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.proship.omrs.venue.repository.VenueMainShardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class VenueMainShardMap implements InitializingBean {

    static final Logger logger = LoggerFactory.getLogger(VenueMainShardMap.class);
    private static BiMap<Long, VenueMainShard> VenueMainShard;

    @Autowired
    VenueMainShardRepository repo;

    @Override
    public void afterPropertiesSet() throws Exception {

        logger.info("[evalTagTypeMap][afterPropertiesSet]start");
        List<VenueMainShard> list = repo.findAll();

        VenueMainShard = HashBiMap.create();

        for (VenueMainShard venueMainShard : list) {
            VenueMainShard.put(venueMainShard.getVenueId(), venueMainShard);
        }

        logger.info("[evalTagTypeMap][afterPropertiesSet]finish setting " + VenueMainShard.size() + " evalTagType types.");
    }

    public static Map getVenueMainShards() {
        return VenueMainShard;
    }


    public static VenueMainShard getVenueMainShard(Long id) {
        return VenueMainShard.get(id);
    }
}
