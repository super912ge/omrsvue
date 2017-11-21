package com.proship.omrs.system.country.repository;

import com.proship.omrs.system.country.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Long> {
}
