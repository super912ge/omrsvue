package com.proship.omrs.contract.repository;

import com.proship.omrs.contract.entity.ContractEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContractEventRepository extends JpaRepository<ContractEvent,Long>{
//
//    @Query("select * from ")
    List<ContractEvent>findAllByContractId(Long id);
}
