package com.proship.omrs.contract.controller;

import com.proship.omrs.contract.entity.ContractEvent;
import com.proship.omrs.contract.repository.ContractEventRepository;
import com.proship.omrs.utils.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/contractEvent/")
public class ContractEventController {
    @Autowired
    ContractEventRepository repository;


    @RequestMapping(value = "search/contractId/{id}", method=RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<Map<String,Object>> findContractEventByContractId(@PathVariable Long id){
        return Utils.getResponseEntityWithResultMap(repository.findAllByContractId(id));
    }

}
