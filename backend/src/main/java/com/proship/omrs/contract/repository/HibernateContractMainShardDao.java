package com.proship.omrs.contract.repository;

import com.proship.omrs.contract.entity.ContractMainShard;
import com.proship.omrs.gig.repository.HibernateGigDao;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
@Repository
@Transactional
public class HibernateContractMainShardDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateContractMainShardDao(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Autowired
    HibernateGigDao gigDao;


    public List<Long> findContractMainShardIdByGigIdAndDate(Long gigId, Date start, Date end ) {

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ContractMainShard.class);

        Criteria contractCriteria = criteria.createCriteria("contract");


        Criteria statusCriteria =  contractCriteria.createCriteria("contractStatus");

        statusCriteria.add(Restrictions.eq("cancel", false));

        Criterion criterionEndTime =
                Restrictions.and(Restrictions.ge("validendtime", end),
                        Restrictions.le("validendtime", start));

        Criterion criterionStartTime =  Restrictions.and(Restrictions.le("validstarttime", end),
                Restrictions.ge("validstarttime", start));


        criteria.add(Restrictions.or(criterionEndTime, criterionStartTime));

//        criteria.addOrder(Order.asc("validendtime"));

        Criteria gigCriteria = criteria.createCriteria("gig");

        gigCriteria.add(Restrictions.eq("id", gigId));

        criteria.setProjection(Projections.distinct(Projections.property("id")));

        criteria.addOrder(Order.asc("id"));

        return criteria.list();
    }
}
