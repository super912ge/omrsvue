package com.proship.omrs.contract.repository;

import com.proship.omrs.contract.entity.Contract;
import com.proship.omrs.contract.param.ContractBrief;
import com.proship.omrs.contract.param.ContractSearchingParamIn;
import com.proship.omrs.gig.entity.PositionMap;
import com.proship.omrs.venue.repository.VenueMainShardRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;


@Repository
@Transactional
public class HibernateContractDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateContractDao(SessionFactory sf) {
        this.sessionFactory=sf;
    }

    @Autowired
    private VenueMainShardRepository venueMainShardRepository;

    public List<Long> findContractByMultiConditions(ContractSearchingParamIn in) {

        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Contract.class);

        criteria.setProjection(Projections.distinct(Projections.property("id")));

        Criteria shardCriteria = criteria.createCriteria("contractMainShards");

        Criteria periodCriteria = criteria.createCriteria("contractPeriodShard");

        Criteria gigCriteria = shardCriteria.createCriteria("gig");

        Criteria gigShardCriteria = gigCriteria.createCriteria("shards");

        Criteria roomCriteria = gigShardCriteria.createCriteria("room");

        if (in.getNonPs()!=null) {

            criteria.add(Restrictions.eq("nonPs", in.getNonPs()));
        }
        if(in.getCanceled()!=null){

            Criteria statusCriteria = criteria.createCriteria("contractStatus");

            statusCriteria.add(Restrictions.eq("cancel", in.getCanceled()));

           // statusCriteria.add(Restrictions.gt("nexttransactiontime",new Date()));
        }

        if (in.getAccountManager()!=null){

            Criteria accountManagerCriteria = gigShardCriteria.createCriteria("responsible");

            accountManagerCriteria.add(Restrictions.eq("id",in.getAccountManager()));

           // accountManagerCriteria.add(Restrictions.gt("nexttransactiontime",new Date()));
        }

        if(in.getExclusive()!=null){
            gigShardCriteria.add(Restrictions.eq("exclusive",in.getExclusive()));
        }

        if(in.getStartDate()!=null){
            periodCriteria.add(Restrictions.ge("validstarttime",in.getStartDate()));
        }

        if(in.getEndDate()!=null){
            periodCriteria.add(Restrictions.le("validendtime",in.getEndDate()));
        }

        if(in.getSeaDate()!=null){

            if (in.getSeaDateOn()) {

                periodCriteria.add(Restrictions.le("validstarttime", in.getSeaDate()));

                periodCriteria.add(Restrictions.ge("validendtime", in.getSeaDate()));
            }else {
                periodCriteria.add(Restrictions.ge("validendtime",in.getSeaDate()));
            }
        }

        if(in.getRank()!=null){

            Set<Long> positions = PositionMap.getPositionIdByGroup(PositionMap.getPosition(in.getRank()).getGrouping());

            shardCriteria.add(Restrictions.in("position",positions));
        }

        if(in.getClientIds()!=null&& !in.getClientIds().isEmpty()){

            if(in.getVenueIds()!=null&& !in.getVenueIds().isEmpty()){

                roomCriteria.add(Restrictions.in("venueId",in.getVenueIds()));
            }else {

                Set<Long> venueIds = venueMainShardRepository.findByClientId(in.getClientIds());

                roomCriteria.add(Restrictions.in("venueId",venueIds));
            }

        }

        if (in.getGigTypeId()!=null){

            gigShardCriteria.add(Restrictions.eq("gigType",in.getGigTypeId()));
        }

        if (in.getRoomName()!=null && !"".equals(in.getRoomName())){

            roomCriteria.add(Restrictions.like("name","%"+in.getRoomName()+"%"));
        }

        if (in.getName()!=null && !"".equals(in.getName())){

            Criteria actCriteria = shardCriteria.createCriteria("act");

            Criteria participantCriteria = actCriteria.createCriteria("participant");

            Criteria nameCriteria = participantCriteria.createCriteria("nameTts");

            nameCriteria.add(Restrictions.ilike("fullName","%"+in.getName().toLowerCase().trim()+"%"));
        }

        List<Long> contracts = criteria.list();



        return contracts;

    }

}
