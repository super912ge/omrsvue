package com.proship.omrs.contract.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.proship.omrs.contract.entity.Contract;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract,Long>{


    List<Contract> findByIdIn(List<Long> ids);
}
