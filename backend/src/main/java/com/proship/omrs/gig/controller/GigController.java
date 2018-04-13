package com.proship.omrs.gig.controller;

import com.proship.omrs.candidate.participant.param.SearchByGigParam;
import com.proship.omrs.exceptions.ErrorDetail;
import com.proship.omrs.gig.param.DisplayGigParam;
import com.proship.omrs.gig.param.DisplayGigResultParam;
import com.proship.omrs.gig.repository.GigRepository;
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

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/gig/")
public class GigController {


    @Autowired
    GigService gigService;

    @Autowired GigRepository repo;

    @RequestMapping(value = "display",method = RequestMethod.POST)
    @ApiOperation(value = "Return gigs' brief information corresponding to given id list.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of gigs' brief information",
                    response = DisplayGigResultParam.class),
            @ApiResponse(code = 409, message = "Illegal argument",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error",response = ErrorDetail.class)})
        public ResponseEntity<DisplayGigResultParam>displayGig(@RequestBody DisplayGigParam param){

            Pageable pageable = new PageRequest(param.getPage(),param.getSize());

            DisplayGigResultParam result = gigService.displayGig(param.getIds(),pageable);

            return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Return qualified gigs' ids corresponding to the given name.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of gigs' ids.",
                    response = Set.class),
            @ApiResponse(code = 409, message = "Illegal argument.",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error.",response = ErrorDetail.class)})
    @RequestMapping(value = "search/name/{name}",method = RequestMethod.GET)
    public ResponseEntity<Set<Long>> searchByName(@PathVariable(value = "name") String name){

        Set<Long> result = gigService.findGigIdByName(name);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @RequestMapping(value = "search/type",method = RequestMethod.POST)
    @ApiOperation(value = "Return qualified gigs' ids corresponding to the given type.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of gigs' ids.",
                    response = Set.class),
            @ApiResponse(code = 409, message = "Illegal argument.",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error.",response = ErrorDetail.class)})
    public ResponseEntity<Set<Long>> searchByGig(@RequestBody SearchByGigParam param){

        Set<Long> result = gigService.findGigIdByRoomAndGigType(param);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @RequestMapping(value = "search/id/{id}",method = RequestMethod.GET)
    @ApiOperation(value = "Return qualified gigs' ids corresponding to the given id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of gigs' ids.",
                    response = Set.class),
            @ApiResponse(code = 409, message = "Illegal argument.",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error.",response = ErrorDetail.class)})
    public ResponseEntity<Set<Long>>searchByGigId(@PathVariable(value = "id")Long id){

        Set<Long> result = new HashSet<>();
        if (repo.findOne(id)!=null){
            result.add(id);
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @RequestMapping(value = "search/accountManager/{userId}",method = RequestMethod.GET)
    @ApiOperation(value = "Return qualified gigs' ids corresponding to the given account manager user id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of gigs' ids.",
                    response = Set.class),
            @ApiResponse(code = 409, message = "Illegal argument.",response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Internal server error.",response = ErrorDetail.class)})
    public ResponseEntity<Set<Long>>searchByAccountManager(@PathVariable(value = "userId")Long id){

        Set<Long> result = gigService.findGigIdByAccountManager(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
