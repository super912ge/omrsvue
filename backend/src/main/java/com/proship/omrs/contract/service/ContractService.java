package com.proship.omrs.contract.service;

import java.util.Map;

import com.proship.omrs.base.service.BaseService;
import com.proship.omrs.contract.entity.Contract;
import com.proship.omrs.contract.param.*;

public interface ContractService extends BaseService{
	
	
	Contract reinstateContract(Long id);

		
	Map<String,Object> generateContractAvailability();

	ContractSearchResultParam findContractByConditions(ContractSearchParamIn in);

}
