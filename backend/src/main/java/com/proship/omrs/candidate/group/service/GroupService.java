package com.proship.omrs.candidate.group.service;

import com.proship.omrs.candidate.group.param.SearchByGigParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupService {

    List<Long> findGroupByGig(SearchByGigParam param);
}
