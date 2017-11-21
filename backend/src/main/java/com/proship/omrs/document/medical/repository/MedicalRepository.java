package com.proship.omrs.document.medical.repository;

import com.proship.omrs.document.base.repository.DocumentRepository;
import com.proship.omrs.document.medical.entity.Medical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface MedicalRepository extends JpaRepository<Medical,Long>, DocumentRepository{


    @Override
    @Query("select a.medical.participantId  from MedicalDetail a " +
            " where a.medical.medicalTypeId in :types and a.expiryDate > :expiryDate " +
            "and a.nexttransactiontime >= CURRENT_DATE group by a.medical.participantId" +
            " having count(a.medical.participantId)= :num ")
    Set<Long> findParticipant(@Param("expiryDate") Date expiryDate, @Param("types") List<Long> types,
                              @Param("num") Long num);
}
