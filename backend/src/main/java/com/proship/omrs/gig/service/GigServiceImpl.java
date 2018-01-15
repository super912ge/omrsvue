package com.proship.omrs.gig.service;

import com.proship.omrs.candidate.group.param.SearchByGigParam;
import com.proship.omrs.contract.repository.ContractShardRepository;
import com.proship.omrs.gig.entity.PositionMap;
import com.proship.omrs.gig.repository.GigMainShardRepository;
import com.proship.omrs.venue.repository.RoomRepository;
import com.proship.omrs.venue.repository.VenueMainShardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.util.Set;

import static java.util.stream.Collectors.toSet;


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
    public Set<Long> findCandidateByGig(com.proship.omrs.candidate.candidate.param.SearchByGigParam param) {

        Set<Long> gigIds = findGigIdByRoomAndGigType(param);

        if (gigIds!= null&& !gigIds.isEmpty()){

            if(param.getRank()!=null){

                Integer group = PositionMap.getPosition(param.getRank()).getGrouping();

                if(group!=null){

                    Set<Long> positions = PositionMap.getPositionIdByGroup(group);
                    if (param.getNonPs()!=null&& param.getNonPs()){
                        return contractShardRepository.findParticipantByGigAndRankGroup(gigIds, positions).stream()
                                .map(BigInteger::longValue).collect(toSet());
                    }else
                        return contractShardRepository.findParticipantByGigAndRankGroupExcludingNonProship(gigIds,positions)
                        .stream().map(BigInteger::longValue).collect(toSet());
                }

                if (param.getNonPs()!=null && param.getNonPs()) {

                    Set<BigInteger> rs = contractShardRepository.findParticipantByGigAndRank(gigIds,param.getRank());
                    return rs.stream().map(BigInteger::longValue).collect(toSet());
                }

                Set<BigInteger> result = contractShardRepository.
                        findParticipantByGigAndRankExcludingNonProship(gigIds,param.getRank());

                return result.stream().map(BigInteger::longValue).collect(toSet());
            }else {

                if (param.getNonPs()!=null&& param.getNonPs()){
                    Set<BigInteger> rs = contractShardRepository.findParticipantByGig(gigIds);
                    return rs.stream().map(BigInteger::longValue).collect(toSet());
                }

                return   contractShardRepository.findParticipantByGigExcludingNonProship(gigIds)
                        .stream().map(BigInteger::longValue).collect(toSet());
            }
        }

        return null;
    }


    public Set<Long> findGigIdByRoomAndGigType(SearchByGigParam param){

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
        return gigIds;

    }
}
