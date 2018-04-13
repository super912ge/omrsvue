package com.proship.omrs.contract.controller;

import com.proship.omrs.contract.entity.ContractEvent;
import com.proship.omrs.contract.repository.ContractEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;



@RestController
@RequestMapping("/contractEvent/")
public class ContractEventController {
    @Autowired
    ContractEventRepository repository;


    @RequestMapping(value = "search/contractId/{id}", method=RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<List<ContractEvent>> findContractEventByContractId(@PathVariable Long id){
        return new ResponseEntity<>(repository.findAllByContractId(id), HttpStatus.OK);
    }

}
