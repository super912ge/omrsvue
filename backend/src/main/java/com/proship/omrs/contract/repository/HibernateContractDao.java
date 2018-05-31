package com.proship.omrs.contract.repository;

import com.proship.omrs.contract.entity.Contract;
import com.proship.omrs.contract.entity.ContractMainShard;
import com.proship.omrs.contract.param.ContractBrief;
import com.proship.omrs.contract.param.ContractSearchResultParam;
import com.proship.omrs.contract.param.ContractSearchParamIn;
import com.proship.omrs.gig.entity.PositionMap;
import com.proship.omrs.gig.repository.HibernateGigDao;
import com.proship.omrs.gig.service.GigService;
import com.proship.omrs.venue.repository.VenueMainShardRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;


@Repository
@Transactional
public class HibernateContractDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateContractDao(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Autowired
    HibernateGigDao gigDao;

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    private VenueMainShardRepository venueMainShardRepository;

    public Map<String, Object> findContractByMultiConditions(ContractSearchParamIn in) {

        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Contract.class);

        Criteria shardCriteria = null;

        Criteria periodCriteria = null;

        Criteria gigCriteria;


        if (in.getGigSearchParam() != null) {

            shardCriteria = criteria.createCriteria("contractMainShards");

            List<Long> gigIds = gigDao.findGigIdByConditions(in.getGigSearchParam());

            gigCriteria = shardCriteria.createCriteria("gig");

            gigCriteria.add(Restrictions.in("id", gigIds));
        }

        if (in.getNonPs() != null) {

            criteria.add(Restrictions.eq("nonPs", in.getNonPs()));
        }
        if (in.getCanceled() != null) {

            Criteria statusCriteria = criteria.createCriteria("contractStatus");

            statusCriteria.add(Restrictions.eq("cancel", in.getCanceled()));

        }


        if (in.getStartDate() != null) {

            periodCriteria = criteria.createCriteria("contractPeriodShard");

            periodCriteria.add(Restrictions.ge("validstarttime", in.getStartDate()));
        }

        if (in.getEndDate() != null) {

            if (periodCriteria == null) periodCriteria = criteria.createCriteria("contractPeriodShard");
            periodCriteria.add(Restrictions.le("validendtime", in.getEndDate()));
        }

        if (in.getSeaDate() != null) {

            if (periodCriteria == null) periodCriteria = criteria.createCriteria("contractPeriodShard");

            if (in.getSeaDateOn()) {

                periodCriteria.add(Restrictions.le("validstarttime", in.getSeaDate()));

                periodCriteria.add(Restrictions.ge("validendtime", in.getSeaDate()));
            } else {
                periodCriteria.add(Restrictions.ge("validendtime", in.getSeaDate()));
            }
        }

        if (in.getRank() != null) {

            Set<Long> positions = PositionMap.getPositionIdByGroup(PositionMap.getPosition(in.getRank()).getGrouping());

            if (shardCriteria == null) shardCriteria = criteria.createCriteria("contractMainShards");
            shardCriteria.add(Restrictions.in("position", positions));
        }


        if (in.getName() != null && !"".equals(in.getName())) {

            if (shardCriteria == null) shardCriteria = criteria.createCriteria("contractMainShards");
            Criteria actCriteria = shardCriteria.createCriteria("act");

            Criteria participantCriteria = actCriteria.createCriteria("participant");

            Criteria nameCriteria = participantCriteria.createCriteria("nameTts");

            nameCriteria.add(Restrictions.ilike("fullName", "%" + in.getName().toLowerCase().trim() + "%"));
        }

        criteria.setFirstResult((int) (in.getPageNumber() - 1) * 20);

        criteria.setMaxResults(20);

        criteria.setProjection(Projections.distinct(Projections.id()));

        List<Long> ids = criteria.list();

        List<Contract> contracts = contractRepository.findByIdIn(ids);

        criteria.setProjection(Projections.countDistinct("id"));

        Long count = (Long) criteria.uniqueResult();

        Map<String, Object> map = new HashMap<>();

        map.put("totalPage", count / 20 + 1);


        map.put("contract", contracts);

        return map;


    }


}
