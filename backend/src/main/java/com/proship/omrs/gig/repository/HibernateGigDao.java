package com.proship.omrs.gig.repository;

import com.proship.omrs.gig.entity.Gig;
import com.proship.omrs.gig.param.GigSearchParam;
import com.proship.omrs.gig.service.GigService;
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
public class HibernateGigDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateGigDao(SessionFactory sf) {
        this.sessionFactory=sf;
    }

    @Autowired
    VenueMainShardRepository venueMainShardRepository;

    @Autowired
    GigService gigService;

    public List<Long> findGigIdByConditions(GigSearchParam in){

        Session session = sessionFactory.getCurrentSession();

        Criteria gigCriteria = session.createCriteria(Gig.class);

        Criteria gigShardCriteria = gigCriteria.createCriteria("shards");

        if (in.getGroup())
            gigCriteria.add(Restrictions.isNull("parentGig"));

        else gigCriteria.add(Restrictions.isNotNull("parentGig"));

        if (in.getAccountManager()!=null){

            Criteria accountManagerCriteria = gigShardCriteria.createCriteria("responsible");

            accountManagerCriteria.add(Restrictions.eq("id",in.getAccountManager()));

        }

        Criteria roomCriteria = null;

        if(in.getClientIds()!=null&& !in.getClientIds().isEmpty()){

            roomCriteria = gigShardCriteria.createCriteria("room");

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

            if(roomCriteria==null)roomCriteria = gigShardCriteria.createCriteria("room");

            roomCriteria.add(Restrictions.like("name","%"+in.getRoomName()+"%"));
        }
        if (in.getRequirement()!=null&&!in.getRequirement().isEmpty()){

            List<Long> gigIds = gigService.findGigIdByRequirement(in.getRequirement());

            gigCriteria.add(Restrictions.in("id",gigIds));
        }
        if (in.getExclusive()!=null && in.getExclusive()){
            gigShardCriteria.add(Restrictions.eq("exclusive",in.getExclusive()));
        }
        if (in.getId()!=null){
            gigCriteria.add(Restrictions.eq("id", in.getId()));
        }
        if (in.getName()!=null&&!"".equals(in.getName())){
            gigShardCriteria.add(Restrictions.ilike("label","%"+in.getName()+"%"));
        }

        gigCriteria.setProjection(Projections.distinct(Projections.property("id")));

        return gigCriteria.list();
    }
}
