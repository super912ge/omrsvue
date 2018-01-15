package com.proship.omrs.candidate.group.service;

import com.proship.omrs.candidate.group.param.BandMemberBrief;
import com.proship.omrs.candidate.group.param.DisplayBandResultParam;
import com.proship.omrs.candidate.group.param.SearchByGigParam;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.util.List;


public interface GroupService {

    List<Long> findGroupByGig(SearchByGigParam param);

    DisplayBandResultParam displayBand(List<Long> ids, Pageable pageable);

    List<BandMemberBrief> getBandMembers(Long id);
}
