package com.proship.omrs.gig.repository;

import com.proship.omrs.base.repository.CustomizedRepository;
import com.proship.omrs.gig.entity.ChairRequirementTag;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ChairRequirementTagRepository extends CustomizedRepository<ChairRequirementTag,Long>{


//    @Query("select ")
//    Set<Long> findRootIdByEvalTagType(Set<Long> id);
}
