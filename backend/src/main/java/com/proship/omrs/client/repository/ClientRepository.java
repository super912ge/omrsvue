package com.proship.omrs.client.repository;

import com.proship.omrs.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long>{
}
