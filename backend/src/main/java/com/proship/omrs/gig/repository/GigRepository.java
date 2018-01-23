package com.proship.omrs.gig.repository;

import com.proship.omrs.gig.entity.Gig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface GigRepository extends JpaRepository<Gig,Long> {

    Page<Gig> findByIdIn(List<Long> ids,Pageable pageable);

//    @Query("select g.id from Gig g where g.id = id")
//    Set<Long> findGigIdById(@Param("id") Long id);


}
