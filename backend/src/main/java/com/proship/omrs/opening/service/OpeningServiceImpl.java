package com.proship.omrs.opening.service;

import com.proship.omrs.base.entity.MainShardEntity;
import com.proship.omrs.base.entity.MainShardEntityWithGig;
import com.proship.omrs.contract.entity.ContractMainShard;
import com.proship.omrs.contract.repository.ContractShardRepository;
import com.proship.omrs.contract.repository.HibernateContractMainShardDao;
import com.proship.omrs.gig.repository.HibernateGigDao;
import com.proship.omrs.opening.entity.RequisitionBts;
import com.proship.omrs.opening.param.Opening;
import com.proship.omrs.opening.param.OpeningSearchParam;
import com.proship.omrs.opening.repository.RequisitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class OpeningServiceImpl implements OpeningService{

    @Autowired
    HibernateGigDao gigDao;

    @Autowired
    HibernateContractMainShardDao contractMainShardDao;

    @Autowired
    ContractShardRepository contractShardRepository;

    @Autowired
    RequisitionRepository requisitionRepository;

    @Override
    public List<Opening> findOpeningsByCondition(OpeningSearchParam in) {

        List<Opening> result = new ArrayList<>();

            List<Long> gigIds = gigDao.findGigIdByConditions(in.getGigSearchParam());

            if (gigIds.isEmpty()) return new ArrayList<>();

            List<RequisitionBts> requisitionBtsList =
                    requisitionRepository.findAllByCondition(gigIds,in.getValidStartDate(),in.getValidEndDate());

            if (in.getGap()) {

                Map<Long, List<MainShardEntityWithGig>> reqMap = generateGigRequisitionMap(requisitionBtsList);

                    List<ContractMainShard> contractShards  =
                            contractMainShardDao.
                                    findContractMainShardIdByGigIdAndDate(
                                            null,gigIds, in.getValidStartDate(), in.getValidEndDate());

                for (ContractMainShard shard : contractShards){

                    Long gigId = shard.getGig().getId();

                    List<MainShardEntityWithGig> mainShardEntityList =
                            reqMap.get(gigId)==null? new ArrayList<>():reqMap.get(gigId);

                    mainShardEntityList.add(shard);

                    mainShardEntityList.sort(Comparator.comparing(MainShardEntity::getValidendtime));
                }

                for (Long id : reqMap.keySet()){

                    List<MainShardEntityWithGig> mainShardEntityList =
                            reqMap.get(id);

                    for (int a = 0; a < mainShardEntityList.size(); a++) {

                        MainShardEntityWithGig shard = mainShardEntityList.get(a);

                        if (mainShardEntityList.get(a) instanceof RequisitionBts) {

                            result.add(new Opening(shard,false));
                        }


                        if (a <= mainShardEntityList.size() - 2 && compareDateDifferenceMoreThanTwoDay
                                (shard.getValidendtime(), mainShardEntityList.get(a + 1).getValidstarttime())) {

                            result.add( new Opening(shard, mainShardEntityList.get(a + 1)));
                        }

                        if (a == mainShardEntityList.size() - 1) {

                            result.add(new Opening(shard, true));
                        }

                    }
                }

            }else {
                for (RequisitionBts requisitionBts1 : requisitionBtsList){
                    result.add(new Opening(requisitionBts1,false));
                }
            }

        return result;
    }

    private static Long dayToMilliseconds(int days){
        return (long)days * 24 * 60 * 60 * 1000;

    }

    private static Boolean compareDateDifferenceMoreThanTwoDay(Date date1, Date date2){
        return date1.getTime() < date2.getTime()-dayToMilliseconds(2);
    }

    private Map<Long,List<MainShardEntityWithGig>> generateGigRequisitionMap(List<RequisitionBts> requisitionBtsList){

        Map<Long, List<MainShardEntityWithGig>> reqMap = new HashMap<>();

        for (RequisitionBts r : requisitionBtsList) {

            Long gigId = r.getGig().getId();

            List<MainShardEntityWithGig> list = reqMap.get(gigId)==null ? new ArrayList<>(): reqMap.get(gigId);

            list.add(r);

            reqMap.put(gigId, list);
        }
        return reqMap;
    }


}
