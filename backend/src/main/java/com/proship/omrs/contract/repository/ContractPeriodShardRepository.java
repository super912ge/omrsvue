package com.proship.omrs.contract.repository;

import com.proship.omrs.base.repository.CustomizedRepository;
import com.proship.omrs.contract.entity.Contract;
import com.proship.omrs.contract.entity.ContractPeriodShard;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;


public interface ContractPeriodShardRepository extends CustomizedRepository<ContractPeriodShard,Long> {

//    @Query(value = "select p.contract.id,p.validstarttime,p.validendtime from ContractPeriodShard p where " +
//            "p.validendtime>= current_date and p.nexttransactiontime> current_timestamp order by p.validendtime ASC")
//    Page<Contract> findContractByValidTime();
}
