package com.proship.omrs.candidate.event.entity;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import com.proship.omrs.candidate.event.repository.ParticipantEventTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class ParticipantEventTypeMap implements InitializingBean{


    static final Logger logger = LoggerFactory.getLogger(ParticipantEventTypeMap.class);
    private static BiMap<Long, ParticipantEventType> participantEventTypes;

    @Autowired
    ParticipantEventTypeRepository repo;

    @Override
    public void afterPropertiesSet() throws Exception {

        logger.info("[participantEventTypeMap][afterPropertiesSet]start");
        List<ParticipantEventType> list = repo.findAll();

        participantEventTypes = HashBiMap.create();

        for (ParticipantEventType participantEventType : list) {
            participantEventTypes.put(participantEventType.getId(), participantEventType);
        }

        logger.info("[participantEventTypeMap][afterPropertiesSet]finish setting " + participantEventTypes.size() + " participantEventType types.");
    }

    public static Map getParticipantEventTypes() {
        return participantEventTypes;
    }


    public static ParticipantEventType getParticipantEventType(Long id) {
        return participantEventTypes.get(id);
    }
    
    
}
