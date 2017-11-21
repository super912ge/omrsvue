package com.proship.omrs.document.seamanBook.repository;

import com.proship.omrs.document.base.repository.DocumentRepository;
import com.proship.omrs.document.seamanBook.entity.SeamansBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface SeamansBookRepository extends JpaRepository<SeamansBook,Long> ,DocumentRepository{

    @Override
    @Query("select a.seamansBook.participantId  from SeamansBookDetail a " +
            " where a.seamansBook.countryId in :types and a.expiryDate > :expiryDate " +
            "and a.nexttransactiontime >= CURRENT_DATE group by a.seamansBook.participantId" +
            " having count(a.seamansBook.participantId)= :num ")
    Set<Long> findParticipant(@Param("expiryDate") Date expiryDate, @Param("types") List<Long> types,
                              @Param("num") Long num);
}
