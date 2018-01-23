package com.proship.omrs.gig.controller;

import com.proship.omrs.base.controller.BaseController;
import com.proship.omrs.candidate.candidate.param.SearchByGigParam;
import com.proship.omrs.gig.entity.Gig;
import com.proship.omrs.gig.param.DisplayGigParam;
import com.proship.omrs.gig.param.DisplayGigResultParam;
import com.proship.omrs.gig.repository.GigRepository;
import com.proship.omrs.gig.service.GigService;
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
public class GigController extends BaseController<Gig,Long> {


    @Autowired
    GigService gigService;


    public GigController(GigRepository repo) {
        super(repo);
    }

    @RequestMapping("display")
        public ResponseEntity<DisplayGigResultParam>displayGig(@RequestBody DisplayGigParam param){
            Pageable pageable = new PageRequest(param.getPage(),param.getSize());
            DisplayGigResultParam result = gigService.displayGig(param.getIds(),pageable);

            return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping("search/name/{name}")
    public ResponseEntity<Set<Long>> searchByName(@PathVariable(value = "name") String name){
        Set<Long> result = gigService.findGigIdByName(name);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @RequestMapping("search/type")
    public ResponseEntity<Set<Long>> searchByGig(@RequestBody SearchByGigParam param){

        Set<Long> result = gigService.findGigIdByRoomAndGigType(param);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @RequestMapping("search/id/{id}")
    public ResponseEntity<Set<Long>>searchByGigId(@PathVariable(value = "id")Long id){
        Set<Long> result = new HashSet<>();
        if (repo.findOne(id)!=null){
            result.add(id);
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @RequestMapping("search/accountManager/{id}")
    public ResponseEntity<Set<Long>>searchByAccountManager(@PathVariable(value = "id")Long id){

        Set<Long> result = gigService.findGigIdByAccountManager(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
