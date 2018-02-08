package com.proship.omrs.candidate.candidate.controller;

import com.proship.omrs.base.controller.BaseController;
import com.proship.omrs.candidate.candidate.entity.Participant;
import com.proship.omrs.candidate.candidate.param.*;
import com.proship.omrs.candidate.candidate.repository.ParticipantRepository;
import com.proship.omrs.candidate.candidate.service.ParticipantService;
import com.proship.omrs.document.base.param.DocumentSearchTerm;
import com.proship.omrs.evaluation.param.EvaluationSearchParam;
import com.proship.omrs.evaluation.service.EvaluationTagService;
import com.proship.omrs.gig.service.GigService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
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

        return new ResponseEntity<>(participantService.findParticipantByDocuments(searchMap), HttpStatus.OK);

    }

    @RequestMapping("/display")
    public ResponseEntity<DisplayCandidateResultParam> listCandidatesByIds(@RequestBody DisplayCandidateParam param) {

       Pageable pageable = new PageRequest(param.getPage(),param.getSize());
       DisplayCandidateResultParam result = participantService.displayCandidate(param.getIds(),pageable);

       return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @RequestMapping("/search/id/{id}")
    public ResponseEntity<Set<Long>> searchCandidateById(@PathVariable(value = "id")Long id){
        Set<Long> result = new HashSet<>();
        if (repo.findOne(id)!=null){
            result.add(id);
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @RequestMapping("/search/actId/{id}")
    public ResponseEntity<Set<Long>> searchCandidateByActId(@PathVariable(value = "id")Long id){
        Set<Long> result = new HashSet<>();
        if (participantService.findParticipantByActId(id)!=null){
            result.add(id);
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
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
    @RequestMapping("/search/name")
    public ResponseEntity<Set<Long>> searchCandidateByName(@RequestBody SearchByNameParam param) {

        Set<Long> results = participantService.findParticipantByName(param);

        return new ResponseEntity<>(results,HttpStatus.OK);
    }

    @RequestMapping("/search/residencyAndCitizenship")
    public ResponseEntity<Set<Long>> searchCandidateByCitizenship(@RequestBody SearchByResidencyCitizenshipParam param){

        Set<Long> results = participantService.findParticipantByCountry(param);

        return new ResponseEntity<>(results,HttpStatus.OK);
    }

    @RequestMapping("/search/availability")
    public ResponseEntity<Set<Long>> searchCandidateByAvailability(@RequestBody SearchByAvailabilityParam param){

        Set<Long> results = participantService.findParticipantByAvailability(param);

        return new ResponseEntity<>(results,HttpStatus.OK);

    }
    @RequestMapping("display/{id}")
    public ResponseEntity<CandidateComplete> displayCandidateDetail(@PathVariable(value = "id")Long id){
        CandidateComplete result = participantService.displayCandidateDetail(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
