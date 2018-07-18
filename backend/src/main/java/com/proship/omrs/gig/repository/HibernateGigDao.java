package com.proship.omrs.gig.repository;


import com.proship.omrs.gig.entity.Gig;
import com.proship.omrs.gig.entity.GigMainShard;
import com.proship.omrs.gig.entity.GigMainShard_;
import com.proship.omrs.gig.entity.Gig_;
import com.proship.omrs.gig.param.GigSearchParam;
import com.proship.omrs.gig.service.GigService;
import com.proship.omrs.user.entity.User;
import com.proship.omrs.user.entity.User_;
import com.proship.omrs.venue.entity.Room;
import com.proship.omrs.venue.entity.Room_;
import com.proship.omrs.venue.repository.VenueMainShardRepository;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class HibernateGigDao {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public HibernateGigDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Autowired
    VenueMainShardRepository venueMainShardRepository;

    @Autowired
    GigService gigService;

    public List<Long> findGigIdByConditions(GigSearchParam in){

        Session session = entityManagerFactory.unwrap(Session.class);

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<Long> criteria = cb.createQuery(Long.class);

        Root<Gig> root = criteria.from(Gig.class);

        Join<Gig,GigMainShard> shardJoin = root.join(Gig_.shards);

        List<Predicate> predicates = new ArrayList<>();

        if (in.getGroup()) {

            predicates.add(cb.isNull(root.get(Gig_.parentGig)));
        }

        else predicates.add(cb.isNotNull(root.get(Gig_.parentGig)));

        if (in.getAccountManager()!=null){

            Join<GigMainShard,User> responsibleJoin = shardJoin.join(GigMainShard_.responsible);

            predicates.add(cb.equal(responsibleJoin.get(User_.id),in.getAccountManager()));

        }

        if (in.getClientIds()!=null||in.getVenueIds()!=null||in.getRoomName()!=null) {

            Join<GigMainShard,Room> roomJoin = shardJoin.join(GigMainShard_.room);

            if (in.getClientIds() != null && !in.getClientIds().isEmpty()) {

                if (in.getVenueIds() != null && !in.getVenueIds().isEmpty()) {

                    predicates.add(roomJoin.get(Room_.venue).in(in.getVenueIds()));

                } else {

                    Set<Long> venueIds = venueMainShardRepository.findByClientId(in.getClientIds());

                    predicates.add(roomJoin.get(Room_.venue).in(venueIds));
                }

            }

            if (in.getRoomName() != null && !"".equals(in.getRoomName())) {

                predicates.add(cb.like(cb.lower(roomJoin.get(Room_.name)),"%" + in.getRoomName().toLowerCase() + "%"));
            }
        }

        if (in.getGigTypeId() != null) {

            predicates.add(cb.equal(shardJoin.get(GigMainShard_.gigType), in.getGigTypeId()));
        }
        if (in.getRequirement()!=null&&!in.getRequirement().isEmpty()){

            List<Long> gigIds = gigService.findGigIdByRequirement(in.getRequirement());

            predicates.add(root.get(Gig_.id).in(gigIds));
        }
        if (in.getExclusive()!=null && in.getExclusive()){
            predicates.add(cb.equal(shardJoin.get(GigMainShard_.exclusive),in.getExclusive()));
        }
        if (in.getId()!=null){
            predicates.add(cb.equal(root.get(Gig_.id), in.getId()));
        }
        if (in.getName()!=null&&!"".equals(in.getName())){
            predicates.add(cb.like(cb.lower(shardJoin.get(GigMainShard_.label)),"%"+in.getName().toLowerCase()+"%"));
        }

        return session.createQuery(criteria.select(root.get(Gig_.id))
                .where(cb.and(predicates.toArray(new Predicate[predicates.size()]))).distinct(true)).getResultList();

    }
}
