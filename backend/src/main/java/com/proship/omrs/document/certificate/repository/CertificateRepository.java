package com.proship.omrs.document.certificate.repository;

import com.proship.omrs.document.base.repository.DocumentRepository;
import com.proship.omrs.document.certificate.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface CertificateRepository extends JpaRepository<Certificate,Long>,
        DocumentRepository {

//    @Query("select a.id from Participant a join a.certificateList b on a.id = b.participantId inner join " +
//            "b.certificate_detail_tts c on b.id = c.certificateId\n" +
//            "  where b.certificateTypeId in :types and c.expiryDate > :expiryDate " +
//            "and c.nexttransactiontime >= CURRENT_DATE group by a.id having count(a.id)= :num ")

    @Override
    @Query("select a.certificate.participantId  from CertificateDetail a " +
            "  where a.certificate.certificateTypeId in :types and a.expiryDate > :expiryDate " +
            "and a.nexttransactiontime >= CURRENT_DATE group by a.certificate.participantId" +
            " having count(a.certificate.participantId)= :num ")
    Set<Long> findParticipant(@Param("expiryDate") Date expiryDate, @Param("types") List<Long> types,
                              @Param("num") Long num);
}
