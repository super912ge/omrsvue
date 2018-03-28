package com.proship.omrs.candidate.participant.service;

import com.proship.omrs.candidate.availability.param.SearchByAvailabilityParam;
import com.proship.omrs.candidate.participant.param.*;
import com.proship.omrs.candidate.citizenship.param.SearchByResidencyCitizenshipParam;
import com.proship.omrs.candidate.name.param.SearchByNameParam;
import com.proship.omrs.document.base.param.DocumentSearchTerm;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public interface ParticipantService {

    //Set<Long> findCandidate()

    Set<Long> findParticipantByDocuments(Map<String,List<DocumentSearchTerm>> searchMap);

    Set<Long> findParticipantByEvaluation(Set<Long> evaluationIds);

//    Set<Long> findParticipantByGig(Set<Long> gigIds);

    Set<Long> findParticipantByName(SearchByNameParam param);

    Set<Long> findParticipantByCountry(SearchByResidencyCitizenshipParam param);

    Set<Long> findParticipantByAvailability(SearchByAvailabilityParam param);

    DisplayCandidateResultParam displayCandidate(List<Long>ids, Pageable pageable);

    Set<Long> findParticipantByActName(String param);

    Long findParticipantByActId(Long actId);

    CandidateComplete displayCandidateDetail(Long id);

    Map<String, Object> createParticipant(CreateParticipantParam param);

    void updateName(Long id, CreateParticipantParam param);
}
