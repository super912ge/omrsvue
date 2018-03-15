package com.proship.omrs.candidate.group.controller;

import com.proship.omrs.base.controller.BaseController;
import com.proship.omrs.candidate.group.entity.Act;
import com.proship.omrs.candidate.group.param.*;
import com.proship.omrs.candidate.group.repository.ActRepository;
import com.proship.omrs.candidate.group.repository.GroupActMainShardRepository;
import com.proship.omrs.candidate.group.repository.GroupActMemberShardRepository;
import com.proship.omrs.candidate.group.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/band/")
@RestController
public class GroupController extends BaseController<Act,Long> {

    @Autowired
    ActRepository actRepository;

    @Autowired
    GroupActMemberShardRepository groupActMemberShardRepository;

    @Autowired
    GroupService groupService;

    @Autowired
    GroupActMainShardRepository groupActMainShardRepository;

    public GroupController(CrudRepository<Act, Long> repo) {
        super(repo);
    }

    @RequestMapping(value = "search/name")
    public ResponseEntity<List<Long>> searchByName(@RequestBody String name){

        name = "%"+name+"%";

        List<Long> result = groupActMainShardRepository.findBandByName(name);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "search/size")
    public ResponseEntity<List<Long>>searchBySize(@RequestBody BandSize size){

        List<Long> result = new ArrayList<>();

        try {
            result = groupActMemberShardRepository.findBandBySize((long)size.getMin(), (long)size.getMax());
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "search/gig")
    public ResponseEntity<List<Long>>searchByGig(@RequestBody SearchByGigParam param){

        List<Long> result = groupService.findGroupByGig(param);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "display")
    public ResponseEntity<DisplayBandResultParam>display(@RequestBody DisplayBandParam param){

        Pageable pageable = new PageRequest(param.getPage(),param.getSize());
        DisplayBandResultParam result = groupService.displayBand(param.getIds(),pageable);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @RequestMapping(value = "members/{id}")
    public ResponseEntity<List<BandMemberBrief>> displayMemebers(@PathVariable(value = "id")Long id){

        List<BandMemberBrief> result = groupService.getBandMembers(id);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
