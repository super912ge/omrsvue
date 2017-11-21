package com.proship.omrs.contract.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proship.omrs.contract.entity.ContractStatus;

public interface ContractStatusRepository extends JpaRepository<ContractStatus,Long>{

}
