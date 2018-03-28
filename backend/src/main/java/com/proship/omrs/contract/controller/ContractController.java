package com.proship.omrs.contract.controller;

import java.util.List;
import java.util.Map;

import com.proship.omrs.utils.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.google.common.collect.Maps;
import com.proship.omrs.base.controller.BaseController;
import com.proship.omrs.contract.entity.Contract;
import com.proship.omrs.contract.repository.ContractRepository;
import com.proship.omrs.contract.service.ContractService;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping(value="/contract/")
@CrossOrigin
public class ContractController extends BaseController<Contract,Long>{
	
	@Autowired
	public ContractController(ContractRepository repo) {
		super(repo);
		
	}
	
	@Autowired
	ContractService contractService;
	
	 @RequestMapping(value="/reinstatecancel/{id}", method=RequestMethod.GET, produces = {"application/json"})
	    public ResponseEntity<Map<String, Object>> reinstateCancelStatus(@PathVariable Long id) {
	    //    Log.debug("create() with body {} of type {}", json, json.getClass());

	        Contract contract = contractService.reinstateContract(id);


	        return Utils.getResponseEntityWithResultMap(contract);
	    }
	 
	 @RequestMapping(value="/search", method=RequestMethod.POST,consumes = {"application/json"})
	 public ResponseEntity<Map<String,Object>>searchContract( @RequestBody Contract contract){
		 

			Logger logger = LoggerFactory.getLogger(this.getClass());
			
			logger.info("searchContract"+contract);
		 
		 List<Contract> contracts = contractService.findContractByConditions(contract);
		 
		 
		return Utils.getResponseEntityWithResultMap(contracts);
		 
	 }


}
