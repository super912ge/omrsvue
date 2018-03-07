package com.proship.omrs.gig.service;

import com.proship.omrs.candidate.participant.param.SearchByGigParam;
import com.proship.omrs.gig.param.DisplayGigResultParam;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Set;

public interface GigService {

    Set<Long> findCandidateByGig(SearchByGigParam param);

    Set<Long> findGigIdByRoomAndGigType(com.proship.omrs.candidate.group.param.SearchByGigParam param);

    DisplayGigResultParam displayGig(List<Long>ids, Pageable pageable);

    Set<Long> findGigIdByName(String name);

    Long findGigIdById(Long id);

    Set<Long>findGigIdByAccountManager(Long id);
}
