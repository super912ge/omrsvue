package com.proship.omrs.contract.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.proship.omrs.contract.param.ContractBrief;
import com.proship.omrs.contract.param.ContractSearchResultParam;
import com.proship.omrs.contract.param.ContractSearchParamIn;
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
	
	 @RequestMapping(value="/reinstateCancel/{id}", method=RequestMethod.GET, produces = {"application/json"})
	    public ResponseEntity<Long> reinstateCancelStatus(@PathVariable Long id) {

	        Contract contract = contractService.reinstateContract(id);

	        return new ResponseEntity<>(contract.getId(),HttpStatus.CREATED);
	    }
	 
	 @RequestMapping(value="/search", method=RequestMethod.POST,consumes = {"application/json"})
	 public ResponseEntity<ContractSearchResultParam>searchContract(@RequestBody ContractSearchParamIn in){
		 

			Logger logger = LoggerFactory.getLogger(this.getClass());
			
			logger.info("searchContract"+ in.toString());
		 
		 ContractSearchResultParam result = contractService.findContractByConditions(in);

		System.out.print( result.getTotalPage());
		 
		return new ResponseEntity<>(result,HttpStatus.OK);
		 
	 }
	 @RequestMapping(value = "/availability",method = RequestMethod.GET)
	 public ResponseEntity<Map<String,Object>> generateAvailability(){
		 return new ResponseEntity<>(new HashMap<>(),HttpStatus.OK);
	 }


}
