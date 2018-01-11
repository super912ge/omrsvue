package com.proship.omrs.gig.service;

import com.proship.omrs.candidate.candidate.param.SearchByGigParam;

import java.util.Set;

public interface GigService {

    Set<Long> findCandidateByGig(SearchByGigParam param);

    Set<Long> findGigIdByRoomAndGigType(com.proship.omrs.candidate.group.param.SearchByGigParam param);
}
