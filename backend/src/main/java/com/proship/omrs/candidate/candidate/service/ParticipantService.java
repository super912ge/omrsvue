package com.proship.omrs.candidate.candidate.service;

import com.proship.omrs.document.base.param.DocumentSearchTerm;
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

}
