package com.proship.omrs.contract.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.proship.omrs.candidate.name.repository.ParticipantNameTtsRepository;
import com.proship.omrs.candidate.participant.repository.ParticipantActRepository;
import com.proship.omrs.contract.param.*;
import com.proship.omrs.contract.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proship.omrs.contract.entity.Contract;

import javax.persistence.criteria.CriteriaBuilder;


@Service
public class ContractServiceImpl implements ContractService{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ContractRepository contractRepo;
	
	@Autowired 
	ContractStatusRepository contractStatusRepo;

	@Autowired
	ContractShardRepository contractShardRepo;

	@Autowired
	ContractPeriodShardRepository contractPeriodShardRepository;

	@Autowired
	ParticipantNameTtsRepository participantNameTtsRepository;

	@Autowired
	ParticipantActRepository participantActRepository;

	@Autowired
	HibernateContractDao hibernateContractDao;

	@Override
	public Contract reinstateContract(Long id) {
		
		Contract contract = contractRepo.findOne(id);
	
//		ContractStatus statusOrig = contract.getContractStatusList().stream()
//				.filter(c->c.getNexttransactiontime()
//						.after(new Timestamp(System.currentTimeMillis())))
//				.collect(Collectors.toList()).get(0);
//
//
//
//		if(contract.getStatus()==true){
//
//			contract.getContractStatusList().remove(statusOrig);
//
//			ContractStatus status =  new ContractStatus();
//
//			statusOrig.setNexttransactiontime( new Timestamp(System.currentTimeMillis()));
//
//			status.setTransactiontime(new Timestamp(System.currentTimeMillis()));
//
//			status.setCancel(false);
//
//			status.setContract(contract);
//
//			status.setId(null);
//
//			status.setCreator(statusOrig.getCreator());
//
//			status.setNexttransactiontime(nextTransactionTime);
//
//			contractStatusRepo.save(statusOrig);
//
//			contractStatusRepo.saveAndFlush(status);
//
//			contract.getContractStatusList().add(statusOrig);
//
//			contract.getContractStatusList().add(status);
//		}

		return contract;
	}


	@Override
	public Map<String, Object> generateContractAvailability() {

//		Page<Contract> contracts = contractPeriodShardRepository.findContractByValidTime();
		return null;
	}

	@Override
	public ContractSearchResultParam findContractByConditions(ContractSearchParamIn in) {

		ContractSearchResultParam resultParam = new
				ContractSearchResultParam();

		Map<String,Object> map = hibernateContractDao.findContractByMultiConditions(in);

		List<Contract> contracts = (List<Contract>) map.get("contract");

		resultParam.setContracts(contracts.stream().map(ContractBrief::new).collect(Collectors.toList()));

		resultParam.setTotalPage((Long)map.get("totalPage"));

		return resultParam;

	}
}
