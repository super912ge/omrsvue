package com.proship.omrs.document.medical.repository;

import com.proship.omrs.document.medical.entity.MedicalType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalTypeRepository extends JpaRepository<MedicalType,Long>{
}
