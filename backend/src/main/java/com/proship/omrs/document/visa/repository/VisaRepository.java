package com.proship.omrs.document.visa.repository;

import com.proship.omrs.document.base.repository.DocumentRepository;
import com.proship.omrs.document.visa.entity.Visa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface VisaRepository extends JpaRepository<Visa,Long>,DocumentRepository{

    @Override
    @Query("select a.visa.participantId  from VisaDetail a " +
            " where a.visa.visaTypeId in :types and a.expiryDate > :expiryDate " +
            "and a.nexttransactiontime >= now() group by a.visa.participantId" +
            " having count(a.visa.participantId)= :num ")
    Set<Long> findParticipant(@Param("expiryDate") Date expiryDate, @Param("types") List<Long> types,
                              @Param("num") Long num);
}
