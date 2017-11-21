package com.proship.omrs.document.passport.repository;

import com.proship.omrs.document.base.repository.DocumentRepository;
import com.proship.omrs.document.passport.entity.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface PassportRepository extends JpaRepository<Passport,Long>,DocumentRepository {


    @Override
    @Query("select a.passport.participantId  from PassportDetail a " +
            " where a.passport.countryId in :types and a.expiryDate > :expiryDate " +
            "and a.nexttransactiontime >= CURRENT_DATE group by a.passport.participantId" +
            " having count(a.passport.participantId)= :num ")
    Set<Long> findParticipant(@Param("expiryDate") Date expiryDate, @Param("types") List<Long> types,
                              @Param("num") Long num);
}
