package com.proship.omrs.evaluation.repository;

import com.proship.omrs.evaluation.entity.EvalTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;


public interface EvalTagRepository extends JpaRepository<EvalTag,Long>{

    @Query("select e.parent.id from EvalTag e where e.nexttransactiontime> current_date  and " +
            "id in :ids group by e.parent.id having count(e.parent.id) = :num ")
    Set<Long> findEvalTagByAllCriteria(@Param("ids") Set<Long> ids, @Param("num") Long num);


    @Query("select e.parent.id from EvalTag e where e.evalTagTypeId in :types and e.nexttransactiontime>CURRENT_DATE " +
            " group by e.parent.id " +
            "having count(e.parent.id) = :num order by e.parent.id")
    Set<Long> findEvalTagIdBySkill(@Param("types")Set<Long>types,@Param("num")Long num);


    //instrument level id
    @Query("select c.parent.id from EvalTag c where c.nexttransactiontime>CURRENT_DATE " +
            "and c.evalTagTypeId = :instrumentType and c.id in " +
            "(select b.parent.id from EvalTag b where b.nexttransactiontime>CURRENT_DATE and b.id in " +
            "(select a.parent.id from EvalTag a where a.nexttransactiontime>CURRENT_DATE " +
            "and a.evalTagTypeId between :rating and 78 )" +
            "and b.evalTagTypeId = :ratingType) ")
    Set<Long>findEvalTagIdByMusicalInstrumentSkillRating(@Param("rating")Long rating,@Param("ratingType")Long ratingType,
                                                         @Param("instrumentType")Long instrumentType );
    //language level id
    @Query("select b.parent.id from EvalTag b where b.evalTagTypeId = :languageType and " +
            "b.nexttransactiontime > current_date and b.id in " +
            "(select a.parent.id from EvalTag a where a.nexttransactiontime> current_date and " +
            "a.evalTagTypeId between :rating and 88)")
    Set<Long>findEvalTagIdByLanguageRating(@Param("rating")Long rating, @Param("languageType")Long languageType );

    //others level id
    @Query("select b.parent.id from EvalTag b where b.nexttransactiontime> current_date and " +
            "b.evalTagTypeId = :evaluationType and b.id in " +
            "(select a.parent.id from EvalTag a where a.nexttransactiontime> current_date and " +
            "a.evalTagTypeId between :rating and 88)")
    Set<Long>findEvalTagIdByClassificationOrPresentationRating(@Param("rating")Long rating,
                                           @Param("evaluationType")Long evaluationType );




}
