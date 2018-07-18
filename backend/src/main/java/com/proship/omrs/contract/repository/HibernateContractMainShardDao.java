package com.proship.omrs.contract.repository;

import com.proship.omrs.contract.entity.*;
import com.proship.omrs.gig.entity.Gig;
import com.proship.omrs.gig.entity.Gig_;
import com.proship.omrs.gig.repository.HibernateGigDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Repository
@Transactional
public class HibernateContractMainShardDao {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public HibernateContractMainShardDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Autowired
    HibernateGigDao gigDao;


    public List<Long> findContractMainShardIdByGigIdAndDate(Long gigId, Date start, Date end ) {

        Session session = entityManagerFactory.unwrap(Session.class);

        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);

        Root<ContractMainShard> root = criteriaQuery.from(ContractMainShard.class);

        List<Predicate> predicates = new ArrayList<>();

        Join<ContractMainShard,Contract> contractJoin = root.join(ContractMainShard_.contract);

        Join<Contract,ContractStatus> statusJoin = contractJoin.join(Contract_.contractStatus);

        predicates.add(builder.equal(statusJoin.get(ContractStatus_.cancel), false));



        Predicate endTimePredicate = builder.and(
                builder.greaterThanOrEqualTo(root.get(ContractMainShard_.validendtime),end),
                builder.lessThanOrEqualTo(root.get(ContractMainShard_.validendtime),start));

        Predicate startTimePredicate = builder.and(
                builder.greaterThanOrEqualTo(root.get(ContractMainShard_.validstarttime),start),
                builder.lessThanOrEqualTo(root.get(ContractMainShard_.validstarttime),end));

        predicates.add(builder.or(endTimePredicate,startTimePredicate));

        Join<ContractMainShard,Gig> gigJoin = root.join(ContractMainShard_.gig);

        predicates.add(builder.equal(gigJoin.get(Gig_.id), gigId));

        criteriaQuery.orderBy(builder.asc(root.get(ContractMainShard_.id)))
                .where(builder.and(predicates.toArray(new Predicate[predicates.size()])))
                .distinct(true)
                .select(root.get(ContractMainShard_.id));

        return session.createQuery(criteriaQuery).getResultList();
    }
}
