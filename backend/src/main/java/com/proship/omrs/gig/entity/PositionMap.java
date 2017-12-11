package com.proship.omrs.gig.entity;


import com.proship.omrs.gig.repository.PositionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PositionMap implements InitializingBean{


    static final Logger logger = LoggerFactory.getLogger(PositionMap.class);
    private static Map<Long, Position> positions;

    @Autowired
    PositionRepository repo;

    @Override
    public void afterPropertiesSet() throws Exception {

        logger.info("[positionMap][afterPropertiesSet]start");
        List<Position> list = repo.findAll();

        positions = new HashMap<>();

        for (Position position : list) {
            positions.put(position.getId(), position);
        }

        logger.info("[positionMap][afterPropertiesSet]finish setting " + positions.size() + " position types.");
    }

    public static Map getPositions() {
        return positions;
    }

    public static Set<Long> getPositionIdByGroup(Integer group ){
        Set<Long> positionSet = new HashSet<>();

        Set<Long> ids = (Set<Long>)PositionMap.getPositions().keySet();

        for(Long id :  ids){
            if(getPosition(id).getGrouping()==group)
                positionSet.add(id);
        }
        return positionSet;
    }
    public static Position getPosition(Long id) {
        return positions.get(id);
    }
}
