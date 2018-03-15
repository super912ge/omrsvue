package com.proship.omrs.candidate.group.service;

import com.proship.omrs.candidate.participant.entity.Participant;
import com.proship.omrs.candidate.participant.entity.ParticipantAct;
import com.proship.omrs.candidate.group.entity.Act;
import com.proship.omrs.candidate.group.param.BandBrief;
import com.proship.omrs.candidate.group.param.BandMemberBrief;
import com.proship.omrs.candidate.group.param.DisplayBandResultParam;
import com.proship.omrs.candidate.group.param.SearchByGigParam;
import com.proship.omrs.candidate.group.repository.ActRepository;
import com.proship.omrs.candidate.group.repository.GroupActMemberShardRepository;
import com.proship.omrs.contract.repository.JobMainShardRepository;
import com.proship.omrs.gig.service.GigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService{

    @Autowired
    GigService gigService;

    @Autowired
    JobMainShardRepository jobMainShardRepository;

    @Autowired
    ActRepository actRepository;

    @Autowired
    GroupActMemberShardRepository groupActMemberShardRepository;

    @Override
    public List<Long> findGroupByGig(SearchByGigParam param) {

        Set<Long> gigIds = gigService.findGigIdByRoomAndGigType(param);

        if(gigIds!=null && !gigIds.isEmpty())

        return jobMainShardRepository.findGroupIdByGigId(gigIds);

        else return new ArrayList<>();

    }

    @Override
    public DisplayBandResultParam displayBand(List<Long> ids, Pageable pageable) {

        Page<Act> resultSet = actRepository.findBandByIdIn(ids, pageable);

        DisplayBandResultParam result = new DisplayBandResultParam();

        List<BandBrief> resultList = resultSet.getContent().stream().map(BandBrief::new).collect(Collectors.toList());

        result.setResultList(resultList);

        result.setTotalPage(resultSet.getTotalPages());

        return result;
    }

    @Override
    public List<BandMemberBrief> getBandMembers(Long id) {

        List<ParticipantAct> participantActs = groupActMemberShardRepository.findBandMembers(id);

        List<Participant> participants = participantActs.stream().map(ParticipantAct::getParticipant).collect(Collectors.toList());

        List<BandMemberBrief> result = participants.stream().map(BandMemberBrief::new).collect(Collectors.toList());

        return result;
    }



}
