package com.proship.omrs.contract.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.proship.omrs.base.service.BaseService;
import com.proship.omrs.contract.entity.Contract;
import com.proship.omrs.contract.param.CandidateSearchParamIn;
import com.proship.omrs.contract.param.ContractBrief;
import com.proship.omrs.contract.param.ContractSearchingParamIn;
import com.proship.omrs.contract.param.GigSearchParamIn;

public interface ContractService extends BaseService{
	
	
	Contract reinstateContract(Long id);

		
	Map<String,Object> generateContractAvailability();

	List<ContractBrief> findContractByConditions(ContractSearchingParamIn in);
}
