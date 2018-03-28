package com.proship.omrs.candidate.participant.controller;

import com.proship.omrs.candidate.availability.param.SearchByAvailabilityParam;
import com.proship.omrs.candidate.citizenship.param.SearchByResidencyCitizenshipParam;
import com.proship.omrs.candidate.name.param.SearchByNameParam;
import com.proship.omrs.candidate.participant.param.*;
import com.proship.omrs.candidate.participant.repository.ParticipantRepository;
import com.proship.omrs.candidate.participant.service.ParticipantService;
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

import java.util.*;

@Log4j
@RestController
@RequestMapping(value="/candidate/")
@CrossOrigin
public class ParticipantController {

    @Autowired
    ParticipantService participantService;

    @Autowired
    ParticipantRepository repo;

    @Autowired
    EvaluationTagService evaluationTagService;

    @Autowired
    GigService gigService;


    @RequestMapping(value = "/search/document",method = RequestMethod.POST)
    public ResponseEntity<Set<Long>> searchCandidateByDocument(@RequestBody Map<String,List<DocumentSearchTerm>> searchMap){

        return new ResponseEntity<>(participantService.findParticipantByDocuments(searchMap), HttpStatus.OK);

    }

    @RequestMapping(value = "/display",method = RequestMethod.POST)
    public ResponseEntity<DisplayCandidateResultParam> listCandidatesByIds(@RequestBody DisplayCandidateParam param) {

       Pageable pageable = new PageRequest(param.getPage(),param.getSize());
       DisplayCandidateResultParam result = participantService.displayCandidate(param.getIds(),pageable);

       return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @RequestMapping(value = "/search/id/{id}",method = RequestMethod.GET)
    public ResponseEntity<Set<Long>> searchCandidateById(@PathVariable(value = "id")Long id){
        Set<Long> result = new HashSet<>();
        if (repo.findOne(id)!=null){
            result.add(id);
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @RequestMapping(value = "/search/actId/{id}",method = RequestMethod.GET)
    public ResponseEntity<Set<Long>> searchCandidateByActId(@PathVariable(value = "id")Long id){
        Set<Long> result = new HashSet<>();

        Long candidateId = participantService.findParticipantByActId(id);
        if (candidateId!=null){
            result.add(candidateId);
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @RequestMapping(value = "/search/evaluation",method = RequestMethod.POST)
    public ResponseEntity<Set<Long>> searchCandidateByEvaluation(@RequestBody EvaluationSearchParam param) {

        Set<Long> result = evaluationTagService.findEvaluationByCriteria(param);
        if (!result.isEmpty()){
            return new ResponseEntity<>(participantService.findParticipantByEvaluation(result),HttpStatus.OK);
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @RequestMapping(value = "/search/gig", method = RequestMethod.POST)
    public ResponseEntity<Set<Long>> searchCandidateByGig(@RequestBody SearchByGigParam param){

        Set<Long> results = gigService.findCandidateByGig(param);

        return new ResponseEntity<>(results,HttpStatus.OK);
    }
    @RequestMapping(value = "/search/name",method = RequestMethod.POST)
    public ResponseEntity<Set<Long>> searchCandidateByName(@RequestBody SearchByNameParam param) {

        Set<Long> results = participantService.findParticipantByName(param);

        return new ResponseEntity<>(results,HttpStatus.OK);
    }

    @RequestMapping(value = "/search/residencyAndCitizenship",method = RequestMethod.POST)
    public ResponseEntity<Set<Long>> searchCandidateByCitizenship(@RequestBody SearchByResidencyCitizenshipParam param){

        Set<Long> results = participantService.findParticipantByCountry(param);

        return new ResponseEntity<>(results,HttpStatus.OK);
    }

    @RequestMapping(value = "search/availability",method = RequestMethod.POST)
    public ResponseEntity<Set<Long>> searchCandidateByAvailability(@RequestBody SearchByAvailabilityParam param){

        Set<Long> results = participantService.findParticipantByAvailability(param);

        return new ResponseEntity<>(results,HttpStatus.OK);

    }
    @RequestMapping(value = "display/{id}",method = RequestMethod.GET)
    public ResponseEntity<CandidateComplete> displayCandidateDetail(@PathVariable(value = "id")Long id){
        CandidateComplete result = participantService.displayCandidateDetail(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @RequestMapping(value = "createByName",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> createNewCandidate(@RequestBody CreateParticipantParam param){

        Map<String,Object> map = participantService.createParticipant(param);

        map.put("success", true);

        return new ResponseEntity<>(map,HttpStatus.OK);
    }
    @RequestMapping(value = "updateName/{id}",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> updateName(@PathVariable(value = "id")Long id,
                                                         @RequestBody CreateParticipantParam param){
        Map<String,Object> map = new HashMap<>();

        this.participantService.updateName(id,param);

        map.put("success", true);

        return new ResponseEntity<>(map,HttpStatus.OK);
    }
}
