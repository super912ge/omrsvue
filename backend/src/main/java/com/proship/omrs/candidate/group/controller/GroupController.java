package com.proship.omrs.candidate.group.controller;

import com.proship.omrs.base.controller.BaseController;
import com.proship.omrs.candidate.group.entity.Act;
import com.proship.omrs.candidate.group.param.BandSize;
import com.proship.omrs.candidate.group.param.SearchByGigParam;
import com.proship.omrs.candidate.group.repository.ActRepository;
import com.proship.omrs.candidate.group.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/band/")
@RestController
public class GroupController extends BaseController<Act,Long> {

    @Autowired
    ActRepository actRepository;

    @Autowired
    GroupService groupService;

    public GroupController(CrudRepository<Act, Long> repo) {
        super(repo);
    }

    @RequestMapping(value = "searchByName")
    public ResponseEntity<List<Long>> searchByName(String name){

        List<Long> result = actRepository.findBandByName(name);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "searchBySize")
    public ResponseEntity<List<Long>>searchBySize(BandSize size){

        List<Long> result = actRepository.findBandBySize(size.getMin(),size.getMax());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "searchByGig")
    public ResponseEntity<List<Long>>searchByGig(SearchByGigParam param){

        List<Long> result = groupService.findGroupByGig(param);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "display")
    public ResponseEntity<List<Act>>display(List<Long> ids){
        return null;
    }
}
