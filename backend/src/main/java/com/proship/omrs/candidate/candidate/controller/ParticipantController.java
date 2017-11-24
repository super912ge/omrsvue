package com.proship.omrs.candidate.candidate.controller;

import com.proship.omrs.base.controller.BaseController;
import com.proship.omrs.candidate.candidate.entity.Participant;
import com.proship.omrs.candidate.candidate.param.SearchByGigParam;
import com.proship.omrs.candidate.repository.ParticipantRepository;
import com.proship.omrs.candidate.candidate.service.ParticipantService;
import com.proship.omrs.document.base.param.DocumentSearchTerm;
import com.proship.omrs.evaluation.param.EvaluationSearchParam;
import com.proship.omrs.evaluation.service.EvaluationTagService;
import com.proship.omrs.gig.service.GigService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Log4j
@RestController
@RequestMapping(value="/candidate/")
@CrossOrigin
public class ParticipantController extends BaseController<Participant,Long>{
    @Autowired
    public ParticipantController(ParticipantRepository repo) {
        super(repo);

    }
    @Autowired
    ParticipantService participantService;

    @Autowired
    EvaluationTagService evaluationTagService;

    @Autowired
    GigService gigService;
    @RequestMapping("/search/document")
    public ResponseEntity<Set<Long>> searchCandidateByDocument(@RequestBody Map<String,List<DocumentSearchTerm>> searchMap){

        return new ResponseEntity<Set<Long>>(participantService.findParticipantByDocuments(searchMap), HttpStatus.OK);

    }

    @RequestMapping("/search/evaluation")
    public ResponseEntity<Set<Long>> searchCandidateByEvaluation(@RequestBody EvaluationSearchParam param) {

        Set<Long> result = evaluationTagService.findEvaluationByCriteria(param);
        if (!result.isEmpty()){
            return new ResponseEntity<>(participantService.findParticipantByEvaluation(result),HttpStatus.OK);
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @RequestMapping("/search/gig")
    public ResponseEntity<Set<Long>> searchCandidateByGig(@RequestBody SearchByGigParam param){

        Set<Long> results = gigService.findCandidateByGig(param);

        return new ResponseEntity<>(results,HttpStatus.OK);
    }
}
