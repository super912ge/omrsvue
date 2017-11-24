package com.proship.omrs.gig.service;

import com.proship.omrs.candidate.candidate.param.SearchByGigParam;
import com.proship.omrs.contract.repository.ContractShardRepository;
import com.proship.omrs.gig.entity.Gig;
import com.proship.omrs.gig.entity.GigMainShard;
import com.proship.omrs.gig.repository.GigMainShardRepository;
import com.proship.omrs.venue.repository.RoomRepository;
import com.proship.omrs.venue.repository.VenueMainShardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class GigServiceImpl implements GigService {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    GigMainShardRepository gigMainShardRepository;

    @Autowired
    ContractShardRepository contractShardRepository;

    @Autowired
    VenueMainShardRepository venueMainShardRepository;
    @Override
    public Set<Long> findCandidateByGig(SearchByGigParam param) {

        Set<Long> rooms = null;

        if (param.getVenueIds()!=null && !param.getVenueIds().isEmpty() ){
           rooms =  roomRepository.findByVenue(param.getVenueIds());
        }else if(param.getClientIds()!=null && !param.getClientIds().isEmpty()){

            Set<Long> venueIds = venueMainShardRepository.findByClientId(param.getClientIds());
            rooms = roomRepository.findByVenue(venueIds);
        }

        Set<Long> gigIds = null;

        if (rooms!=null&& !rooms.isEmpty()) {
            if (param.getGigTypeId()!=null)
            gigIds = gigMainShardRepository.findGigIdByRoomAndGigType(rooms,param.getGigTypeId());
            else
                gigIds = gigMainShardRepository.findGigIdByRoom(rooms);
        }
        if (param.getGigTypeId()!=null&&gigIds==null) {

                gigIds = gigMainShardRepository.findGigIdByGigType(param.getGigTypeId());
        }

        if (gigIds!= null&& !gigIds.isEmpty()){

            return   contractShardRepository.findParticipantByGig(gigIds);

        }

        return null;
    }
}
