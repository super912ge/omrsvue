package com.proship.omrs.contract.controller;

import com.proship.omrs.base.controller.BaseController;
import com.proship.omrs.contract.entity.Contract;
import com.proship.omrs.contract.entity.ContractEvent;
import com.proship.omrs.contract.repository.ContractEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/contractEvent/")
public class ContractEventController extends BaseController<ContractEvent,Long>{
    @Autowired
    ContractEventRepository repository;
    public ContractEventController(CrudRepository<ContractEvent, Long> repo) {
        super(repo);
    }

    @RequestMapping(value = "search/contractId/{id}", method=RequestMethod.GET, produces = {"application/json"})
    public List<ContractEvent> findContractEventByContractId(@PathVariable Long id){
        return repository.findAllByContractId(id);
    }

}
