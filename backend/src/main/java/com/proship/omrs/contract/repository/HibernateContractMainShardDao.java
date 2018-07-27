package com.proship.omrs.contract.repository;

import com.proship.omrs.contract.entity.*;
import com.proship.omrs.gig.entity.Gig;
import com.proship.omrs.gig.entity.Gig_;
import com.proship.omrs.gig.repository.HibernateGigDao;
import com.proship.omrs.utils.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Repository
@Transactional
public class HibernateContractMainShardDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    HibernateGigDao gigDao;




    @Transactional
    public List<ContractMainShard> findContractMainShardIdByGigIdAndDate(Long gigId,List<Long>gigIds, Date start, Date end ) {

        //Session session = entityManagerFactory.unwrap(Session.class);

        EntityManager  entityManager = entityManagerFactory.createEntityManager();


        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<ContractMainShard> criteriaQuery = builder.createQuery(ContractMainShard.class);

        Root<ContractMainShard> root = criteriaQuery.from(ContractMainShard.class);

        List<Predicate> predicates = new ArrayList<>();

        Join<ContractMainShard,Gig> gigJoin = root.join("gig");

        Join<ContractMainShard,Contract> contractJoin = root.join(ContractMainShard_.contract);

        Join<Contract,ContractStatus> statusJoin = contractJoin.join(Contract_.contractStatus);

        predicates.add(builder.equal(statusJoin.get(ContractStatus_.cancel), false));

        end = end==null? Utils.getInfiniteTimestamp(): end;

        start = start==null ? new Date(System.currentTimeMillis()):start;

        Predicate endTimePredicate = builder.and(
                builder.greaterThanOrEqualTo(root.get(ContractMainShard_.validendtime),end),
                builder.lessThanOrEqualTo(root.get(ContractMainShard_.validendtime),start));

        Predicate startTimePredicate = builder.and(
                builder.greaterThanOrEqualTo(root.get(ContractMainShard_.validstarttime),start),
                builder.lessThanOrEqualTo(root.get(ContractMainShard_.validstarttime),end));

        predicates.add(builder.or(endTimePredicate,startTimePredicate));

        if (gigId!=null)
            predicates.add(builder.equal(gigJoin.get(Gig_.id), gigId));
        else if (gigIds!=null&&!gigIds.isEmpty())
            predicates.add(gigJoin.get(Gig_.id).in(gigIds));

        criteriaQuery.orderBy(builder.asc(root.get(ContractMainShard_.gig).get(Gig_.id)),
                builder.asc(root.get(ContractMainShard_.validendtime)))
                .where(builder.and(predicates.toArray(new Predicate[predicates.size()])))
                .distinct(true)
                .select(root);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}
