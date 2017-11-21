package com.proship.omrs.contract.service;

import java.util.List;

import com.proship.omrs.base.service.BaseService;
import com.proship.omrs.contract.entity.Contract;

public interface ContractService extends BaseService{
	
	
	Contract reinstateContract(Long id);

	List<Contract> findContractByConditions(Contract contract);
		
	

}
