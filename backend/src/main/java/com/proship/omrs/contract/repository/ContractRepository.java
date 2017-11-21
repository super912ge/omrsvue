package com.proship.omrs.contract.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import com.proship.omrs.contract.entity.Contract;

public interface ContractRepository extends JpaRepository<Contract,Long>,QueryDslPredicateExecutor<Contract>{
	
	


}
