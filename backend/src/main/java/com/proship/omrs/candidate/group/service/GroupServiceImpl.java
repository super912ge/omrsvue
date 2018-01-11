package com.proship.omrs.candidate.group.service;

import com.proship.omrs.candidate.group.param.SearchByGigParam;
import com.proship.omrs.contract.repository.JobMainShardRepository;
import com.proship.omrs.gig.service.GigService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

public class GroupServiceImpl implements GroupService{

    @Autowired
    GigService gigService;

    @Autowired
    JobMainShardRepository jobMainShardRepository;

    @Override
    public List<Long> findGroupByGig(SearchByGigParam param) {

        Set<Long> gigIds = gigService.findGigIdByRoomAndGigType(param);

        return jobMainShardRepository.findGroupIdByGigId(gigIds);


    }
}
