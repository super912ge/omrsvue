package com.proship.omrs.contract.repository;

import com.proship.omrs.candidate.name.entity.ParticipantNameTts;
import com.proship.omrs.candidate.name.entity.ParticipantNameTts_;
import com.proship.omrs.candidate.participant.entity.Participant;
import com.proship.omrs.candidate.participant.entity.ParticipantAct;
import com.proship.omrs.candidate.participant.entity.ParticipantAct_;
import com.proship.omrs.candidate.participant.entity.Participant_;
import com.proship.omrs.contract.entity.*;
import com.proship.omrs.contract.param.ContractBriefWithParticipant;
import com.proship.omrs.contract.param.ContractSearchParamIn;
import com.proship.omrs.contract.param.ContractSearchResultParam;
import com.proship.omrs.gig.entity.Gig;
import com.proship.omrs.gig.entity.Gig_;
import com.proship.omrs.gig.entity.PositionMap;
import com.proship.omrs.gig.repository.HibernateGigDao;
import com.proship.omrs.venue.entity.Room;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.*;
import java.util.stream.Collectors;


@Repository
@Transactional
public class HibernateContractDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private final Integer pageSize = 20;

    @Autowired
    HibernateGigDao gigDao;

    @Autowired
    ContractRepository contractRepository;

    public ContractSearchResultParam findContractByMultiConditions(ContractSearchParamIn in) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Contract> criteria = builder.createQuery(Contract.class);

        Root<Contract> root = criteria.from(Contract.class);

        List<Predicate> contractPredicates = new ArrayList<>();

        Join<Contract,ContractMainShard> contractMainShardJoin =root.join(Contract_.contractMainShards);

        Join<Contract,ContractPeriodShard> periodShardJoin = root.join(Contract_.contractPeriodShard);

        Join<ContractMainShard,Gig> gigJoin ;

        if (in.getId()!=null){
            contractPredicates.add(builder.equal(root.get(Contract_.id),in.getId()));
        }

        if (in.getGigSearchParam() != null) {

            List<Long> gigIds = gigDao.findGigIdByConditions(in.getGigSearchParam());

            gigJoin = contractMainShardJoin.join(ContractMainShard_.gig);

            contractPredicates.add(gigJoin.get(Gig_.id).in(gigIds));


        }

        if (in.getNonPs() != null) {

            contractPredicates.add(builder.notEqual(root.get(Contract_.nonPs),in.getNonPs()));
        }

        if (in.getCanceled() != null) {

            contractPredicates.add(builder.equal(root.join(Contract_.contractStatus).get(ContractStatus_.cancel),in.getCanceled()));

        }

        if (in.getStartDate() != null) {

            contractPredicates.add(builder.greaterThan(
                    periodShardJoin.get(ContractPeriodShard_.validstarttime),in.getStartDate()));
        }

        if (in.getEndDate() != null) {

            contractPredicates.add(builder.lessThan(
                    periodShardJoin.get(ContractPeriodShard_.validendtime),in.getEndDate()));
        }

        if (in.getSeaDate() != null) {

            if (in.getSeaDateOn()) {

                Predicate seaDateOn = builder.and(builder.lessThan(
                        periodShardJoin.get(ContractPeriodShard_.validstarttime),in.getSeaDate()),builder.greaterThan(
                        periodShardJoin.get(ContractPeriodShard_.validendtime),in.getSeaDate()));

                contractPredicates.add(seaDateOn);

            } else {

                contractPredicates.add(builder.greaterThan(
                        periodShardJoin.get(ContractPeriodShard_.validendtime),in.getSeaDate()));
            }
        }

        if (in.getRank() != null) {

            Set<Long> positions = PositionMap.getPositionIdByGroup(PositionMap.getPosition(in.getRank()).getGrouping());

            contractPredicates.add(contractMainShardJoin.get(ContractMainShard_.position).in(positions));
        }


        if (in.getName() != null && !"".equals(in.getName())) {

            Join<ContractMainShard,ParticipantAct> participantActJoin = contractMainShardJoin.join(ContractMainShard_.act);

            Join<ParticipantAct,Participant> participantJoin = participantActJoin.join(ParticipantAct_.participant);

            Join<Participant,ParticipantNameTts> nameTtsJoin = participantJoin.join(Participant_.nameTts);

            contractPredicates.add(builder.like(builder.lower(
                    nameTtsJoin.get(ParticipantNameTts_.fullName)),"%" + in.getName().toLowerCase().trim() + "%"));

        }


        Predicate predicateFinal = contractPredicates.size()>1?
                builder.and(contractPredicates.toArray(new Predicate[contractPredicates.size()])):contractPredicates.get(0);

        long count = 0;

//        if(in.getPageNumber()==0){
//            CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
//
//            Root<Contract> rootCount =  countQuery.from(Contract.class);
//
//            Expression<Long> id = rootCount.get(Contract_.id);
//
//            countQuery.select(builder.countDistinct(id)).where(predicateFinal);

//
//            try {
//                TypedQuery<Long> typedQuery = entityManager.createQuery(countQuery);
//                count = typedQuery.getSingleResult();
//            }catch (Exception e){
//                e.printStackTrace();
//            }


  //      }


        ContractSearchResultParam resultParam = new ContractSearchResultParam();

//        Map<String, Object> map = new HashMap<>();
//
//        map.put("totalPage", count / 20 + 1);
//
        resultParam.setTotalPage(count / 20 + 1);

        try {

            TypedQuery<Contract> typedQueryResult = entityManager.createQuery(criteria.where(predicateFinal)
                    .orderBy(builder.desc(root.get(Contract_.id))).distinct(true))
                    .setMaxResults(pageSize).setFirstResult(in.getPageNumber().intValue() * pageSize);

            List<ContractBriefWithParticipant> contracts = typedQueryResult.getResultList()
                    .stream().map(ContractBriefWithParticipant::new).collect(Collectors.toList());

            System.out.print(contracts.get(0));

           resultParam.setContracts(contracts);
        }catch (Exception e){
            e.printStackTrace();
        }



        return resultParam;


    }


}
