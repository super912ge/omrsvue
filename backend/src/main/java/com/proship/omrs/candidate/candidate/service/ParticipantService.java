package com.proship.omrs.candidate.candidate.service;

import com.proship.omrs.candidate.candidate.param.*;
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
}
