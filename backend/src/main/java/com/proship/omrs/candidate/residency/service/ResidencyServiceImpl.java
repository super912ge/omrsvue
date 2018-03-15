package com.proship.omrs.candidate.residency.service;

import com.proship.omrs.candidate.base.entity.BaseEntityCountry;
import com.proship.omrs.candidate.base.entity.BaseOverrideEntity;
import com.proship.omrs.candidate.base.service.CandidateBaseServiceImpl;
import com.proship.omrs.candidate.residency.entity.ParticipantResidency;
import com.proship.omrs.candidate.residency.entity.ParticipantResidencyOverride;
import com.proship.omrs.candidate.residency.repository.ParticipantResidencyOverrideRepository;
import com.proship.omrs.candidate.residency.repository.ParticipantResidencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResidencyServiceImpl extends
        CandidateBaseServiceImpl<ParticipantResidency,ParticipantResidencyOverride> implements ResidencyService {

    @Autowired
    ParticipantResidencyRepository participantResidencyRepository;

    @Autowired
    ParticipantResidencyOverrideRepository participantResidencyOverrideRepository;

    @Override
    public List<ParticipantResidency> addResidency(Long id, List<Long> countryIds) {

        List<ParticipantResidency> result = new ArrayList<>();

        for (Long countryId :countryIds) {


            ParticipantResidency participantResidency = getNewBaseEntity(new ParticipantResidency());

            participantResidency.setCountryId(countryId);

            participantResidency = participantResidencyRepository.save(participantResidency);

            ParticipantResidencyOverride participantResidencyOverride =
                    getNewBaseOverrideEntity(new ParticipantResidencyOverride(),id);

            participantResidencyOverride.setId(participantResidency.getId());

            participantResidencyOverride.setParticipantResidency(participantResidency);

            participantResidencyOverrideRepository.save(participantResidencyOverride);

            result.add(participantResidency);
        }

        return result;
    }

    @Override
    public List<ParticipantResidency> update(Long candidateId, List<Long> ids) {



        List<ParticipantResidencyOverride> list = participantResidencyOverrideRepository
                .findByParticipantAndAndNexttransactiontimeIsAfter(candidateId, new Timestamp(System.currentTimeMillis()));

        List<ParticipantResidencyOverride> toBeDeleted = list.stream()
                .filter(item-> !ids.contains(item.getParticipantResidency().getCountryId())).collect(Collectors.toList());

        list.removeAll(toBeDeleted);

        toBeDeleted = toBeDeleted.stream().peek(item->item.setNexttransactiontime(new Timestamp(System.currentTimeMillis())))
                .collect(Collectors.toList());

        participantResidencyOverrideRepository.save(toBeDeleted);

        List<Long> existingCountryIds = list.stream().map(ParticipantResidencyOverride::getParticipantResidency)
                .map(ParticipantResidency::getCountryId).collect(Collectors.toList());

        ids.removeAll(existingCountryIds);

        List<ParticipantResidency> added = addResidency(candidateId,existingCountryIds);

        List <ParticipantResidency> finalList = list.stream()
                .map(ParticipantResidencyOverride::getParticipantResidency).collect(Collectors.toList());
        finalList.addAll(added);

        return finalList;


    }

    @Override
    public Long delete(List<Long> ids) {

        List<ParticipantResidencyOverride> deleted = participantResidencyOverrideRepository.findByIdIn(ids);

        deleted = deleted.stream().peek(item-> item.setNexttransactiontime(new Timestamp(System.currentTimeMillis())))
                .collect(Collectors.toList());
         participantResidencyOverrideRepository.save(deleted);

         return deleted.get(0).getParticipant().getId();
    }

    @Override
    public Long delete(Long id){


        List<ParticipantResidencyOverride> deleted = participantResidencyOverrideRepository
                .findByParticipantAndAndNexttransactiontimeIsAfter(id,new Timestamp(System.currentTimeMillis()));

        if (deleted.isEmpty()) return id;
        deleted = deleted.stream().peek(item-> item.setNexttransactiontime(new Timestamp(System.currentTimeMillis())))
                .collect(Collectors.toList());
        participantResidencyOverrideRepository.save(deleted);

        return deleted.get(0).getParticipant().getId();
    }
}
