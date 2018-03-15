package com.proship.omrs.contract.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proship.omrs.contract.entity.Contract;
import com.proship.omrs.contract.repository.ContractRepository;
import com.proship.omrs.contract.repository.ContractShardRepository;
import com.proship.omrs.contract.repository.ContractStatusRepository;



@Service
public class ContractServiceImpl implements ContractService{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ContractRepository contractRepo;
	
	@Autowired 
	ContractStatusRepository contractStatusRepo;
	
	
	@Autowired
	ContractShardRepository contractShardRepo;

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
	
	public List<Contract> findContractByConditions(Contract contract){
		
		
		logger.info(contract.toString());
//
//		ContractShard shard = contract.getContractShard();
//		QContractShard Qshard = QContractShard.contractShard;
//		BooleanExpression starttime = null ;
//		BooleanExpression endtime = null;
//		BooleanExpression gig = null;
//		BooleanExpression act = null;
//
//		BooleanExpression transactiontime = Qshard.nexttransactiontime.after(new Timestamp(System.currentTimeMillis()));
//
//		if(shard!=null){
//
//			if(shard.getValidstarttime()!=null) {
//				starttime = Qshard.validstarttime.after(shard.getValidstarttime());
//			}
//			if(shard.getValidendtime()!=null) {
//				endtime = Qshard.validendtime.before(shard.getValidendtime());
//			}
//			if(shard.getGig_id()!=null){
//				gig = Qshard.gig_id.eq(shard.getGig_id());
//			}
//			if(shard.getAct()!=null){
//				act = Qshard.gig_id.eq(shard.getAct());
//			}
//		}
//		List<ContractShard> list = (List<ContractShard>)contractShardRepo.findAll(transactiontime.and(starttime).and(gig).and(endtime).and(act));
//
//		List<Contract> contracts = list.stream().map(ContractShard::getContract).collect(Collectors.toList());
//
//
//		return contracts;
		return null;
//
	}

}
