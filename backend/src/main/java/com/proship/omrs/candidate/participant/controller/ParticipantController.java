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
import com.proship.omrs.exceptions.ErrorDetail;
import com.proship.omrs.gig.service.GigService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


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
    @ApiOperation(value = "Return qualified candidates' id list according to given document requirements.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of participants' ids", response = Set.class),
            @ApiResponse(code = 409, message = "Illegal argument",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error",response = ErrorDetail.class)})
    public ResponseEntity<Set<Long>> searchCandidateByDocument(@RequestBody Map<String,List<DocumentSearchTerm>> searchMap){

        return new ResponseEntity<>(participantService.findParticipantByDocuments(searchMap), HttpStatus.OK);

    }

    @RequestMapping(value = "/display",method = RequestMethod.POST)
    @ApiOperation(value = "Return candidates' brief information corresponding to given id list.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of participants' brief information",
                    response = DisplayCandidateResultParam.class),
            @ApiResponse(code = 409, message = "Illegal argument",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error",response = ErrorDetail.class)})
    public ResponseEntity<DisplayCandidateResultParam> listCandidatesByIds(@RequestBody DisplayCandidateParam param) {

       Pageable pageable = new PageRequest(param.getPage(),param.getSize());
       DisplayCandidateResultParam result = participantService.displayCandidate(param.getIds(),pageable);

       return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @RequestMapping(value = "/search/id/{candidateId}",method = RequestMethod.GET)
    @ApiOperation(value = "Return qualified candidates' ids according to the given id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of qualified participants' ids.",
                    response = Set.class),
            @ApiResponse(code = 409, message = "Illegal argument",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error",response = ErrorDetail.class)})
    public ResponseEntity<Set<Long>> searchCandidateById(@PathVariable(value = "candidateId")Long id){
        Set<Long> result = new HashSet<>();
        if (repo.findById(id)!=null){
            result.add(id);
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }


    @RequestMapping(value = "/search/actId/{actId}",method = RequestMethod.GET)
    @ApiOperation(value = "Return qualified candidates' ids according to the given candidate's act id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of qualified participants' ids.",
                    response = Set.class),
            @ApiResponse(code = 409, message = "Illegal argument",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error",response = ErrorDetail.class)})
    public ResponseEntity<Set<Long>> searchCandidateByActId(@PathVariable(value = "actId")Long id){
        Set<Long> result = new HashSet<>();

        Long candidateId = participantService.findParticipantByActId(id);
        if (candidateId!=null){
            result.add(candidateId);
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }


    @RequestMapping(value = "/search/evaluation",method = RequestMethod.POST)
    @ApiOperation(value = "Return qualified participants' ids according to given evaluation requirement tags.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of qualified participants' ids", response = Set.class),
            @ApiResponse(code = 409, message = "Illegal argument",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error",response = ErrorDetail.class)})
    public ResponseEntity<Set<Long>> searchCandidateByEvaluation(@RequestBody EvaluationSearchParam param) {

        Set<Long> result = evaluationTagService.findEvaluationByCriteria(param);
        if (!result.isEmpty()){
            return new ResponseEntity<>(participantService.findParticipantByEvaluation(result),HttpStatus.OK);
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }


    @RequestMapping(value = "/search/gig", method = RequestMethod.POST)
    @ApiOperation(value = "Return qualified participants' ids according to given gig requirements.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of qualified participants' ids.", response = Set.class),
            @ApiResponse(code = 409, message = "Illegal argument",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error",response = ErrorDetail.class)})
    public ResponseEntity<Set<Long>> searchCandidateByGig(@RequestBody SearchByGigParam param){

        Set<Long> results = gigService.findCandidateByGig(param);

        return new ResponseEntity<>(results,HttpStatus.OK);
    }


    @RequestMapping(value = "/search/name",method = RequestMethod.POST)
    @ApiOperation(value = "Return qualified participants' ids according to given letters and searching method.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of qualified participants' ids.", response = Set.class),
            @ApiResponse(code = 409, message = "Illegal argument",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error",response = ErrorDetail.class)})
    public ResponseEntity<Set<Long>> searchCandidateByName(@RequestBody SearchByNameParam param) {

        Set<Long> results = participantService.findParticipantByName(param);

        return new ResponseEntity<>(results,HttpStatus.OK);
    }


    @RequestMapping(value = "/search/residencyAndCitizenship",method = RequestMethod.POST)
    @ApiOperation(value = "Return qualified participants' ids according to given " +
            "citizenship and/or residency country list requirements.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of qualified participants' ids.", response = Set.class),
            @ApiResponse(code = 409, message = "Illegal argument",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error",response = ErrorDetail.class)})
    public ResponseEntity<Set<Long>> searchCandidateByCitizenshipResidency(@RequestBody SearchByResidencyCitizenshipParam param){

        Set<Long> results = participantService.findParticipantByCountry(param);

        return new ResponseEntity<>(results,HttpStatus.OK);
    }


    @RequestMapping(value = "search/availability",method = RequestMethod.POST)
    @ApiOperation(value = "Return qualified participants' ids according to given letters and searching method.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of qualified participants' ids.", response = Set.class),
            @ApiResponse(code = 409, message = "Illegal argument",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error",response = ErrorDetail.class)})
    public ResponseEntity<Set<Long>> searchCandidateByAvailability(@RequestBody SearchByAvailabilityParam param){

        Set<Long> results = participantService.findParticipantByAvailability(param);

        return new ResponseEntity<>(results,HttpStatus.OK);

    }


    @RequestMapping(value = "display/{id}",method = RequestMethod.GET)
    @ApiOperation(value = "Return candidate's detail information according to the given candidate's id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of qualified participants' ids.",
                    response = CandidateComplete.class),
            @ApiResponse(code = 409, message = "Illegal argument",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error",response = ErrorDetail.class)})
    public ResponseEntity<CandidateComplete> displayCandidateDetail(@PathVariable(value = "id")Long id){

        CandidateComplete result = participantService.displayCandidateDetail(id);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }


    @RequestMapping(value = "createByName",method = RequestMethod.POST)
    @ApiOperation(value = "Create a new candidate and return the created candidate's id.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully creating a participant and inserting his/her basic information.",
                    response = Map.class),
            @ApiResponse(code = 409, message = "Illegal argument",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error",response = ErrorDetail.class)})
    public ResponseEntity<Map<String,Object>> createNewCandidate(@RequestBody CreateParticipantParam param){

        Map<String,Object> map = participantService.createParticipant(param);

        return new ResponseEntity<>(map,HttpStatus.CREATED);
    }


    @RequestMapping(value = "updateName/{id}",method = RequestMethod.POST)
    @ApiOperation(value = "Update candidate's basic information, as name, act name, gender, and birthday, return candidate's id on success.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful updated candidate.",
                    response = Long.class),
            @ApiResponse(code = 409, message = "Illegal argument",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error",response = ErrorDetail.class)})
    public ResponseEntity<Long> updateName(@PathVariable(value = "id")Long id,
                                                         @RequestBody CreateParticipantParam param){

        this.participantService.updateName(id,param);

        return new ResponseEntity<>(id,HttpStatus.CREATED);
    }
}
