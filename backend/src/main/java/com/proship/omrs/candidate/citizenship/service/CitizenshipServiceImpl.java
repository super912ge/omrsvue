package com.proship.omrs.candidate.citizenship.service;


import com.proship.omrs.candidate.base.service.CandidateBaseServiceImpl;
import com.proship.omrs.candidate.citizenship.entity.ParticipantCitizenshipOverride;
import com.proship.omrs.candidate.citizenship.repository.ParticipantCitizenshipRepository;
import com.proship.omrs.candidate.citizenship.entity.ParticipantCitizenship;
import com.proship.omrs.candidate.citizenship.repository.ParticipantCitizenshipOverrideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitizenshipServiceImpl extends
        CandidateBaseServiceImpl<ParticipantCitizenship, ParticipantCitizenshipOverride>
        implements CitizenshipService{

    @Autowired
    ParticipantCitizenshipOverrideRepository participantCitizenshipOverrideRepository;

    @Autowired
    ParticipantCitizenshipRepository participantCitizenshipRepository;


    @Override
    public List<ParticipantCitizenship> addCitizenship(Long id, List<Long> countryIds) {


        List<ParticipantCitizenship> ids = new ArrayList<>();

        for (Long countryId: countryIds){

            ParticipantCitizenship  participantCitizenship = getNewBaseEntity(new ParticipantCitizenship());

            participantCitizenship.setCountryId(countryId);

                participantCitizenship = participantCitizenshipRepository.save(participantCitizenship);

                ParticipantCitizenshipOverride participantCitizenshipOverride =
                        getNewBaseOverrideEntity(new ParticipantCitizenshipOverride(),id);

                participantCitizenshipOverride.setId(participantCitizenship.getId());

                participantCitizenshipOverride.setParticipantCitizenship(participantCitizenship);

                participantCitizenshipOverrideRepository.save(participantCitizenshipOverride);

                ids.add(participantCitizenship);
        }
        return ids;
    }

    @Override
    public List<ParticipantCitizenship> update(Long candidateId, List<Long>ids) {

        List<ParticipantCitizenshipOverride> list = participantCitizenshipOverrideRepository
                .findByParticipantAndAndNexttransactiontimeIsAfter(candidateId, new Timestamp(System.currentTimeMillis()));

        List<ParticipantCitizenshipOverride> toBeDeleted = list.stream()
                .filter(item-> !ids.contains(item.getParticipantCitizenship().getCountryId())).collect(Collectors.toList());

        list.removeAll(toBeDeleted);

        toBeDeleted = toBeDeleted.stream().peek(item->item.setNexttransactiontime(new Timestamp(System.currentTimeMillis())))
                .collect(Collectors.toList());

        participantCitizenshipOverrideRepository.save(toBeDeleted);

        List<Long> existingCountryIds = list.stream().map(ParticipantCitizenshipOverride::getParticipantCitizenship)
                .map(ParticipantCitizenship::getCountryId).collect(Collectors.toList());

        ids.removeAll(existingCountryIds);

        List<ParticipantCitizenship> added = addCitizenship(candidateId,existingCountryIds);

        List <ParticipantCitizenship> finalList = list.stream()
                .map(ParticipantCitizenshipOverride::getParticipantCitizenship).collect(Collectors.toList());

        finalList.addAll(added);

        return finalList;
    }

    @Override
    public Long delete(List<Long> ids) {

        List<ParticipantCitizenshipOverride> deleteList =
                participantCitizenshipOverrideRepository.findParticipantCitizenshipOverrideByIdIn(ids);

        deleteList = deleteList.stream().peek(item->item.setNexttransactiontime(new Timestamp(System.currentTimeMillis())))
                .collect(Collectors.toList());

        participantCitizenshipOverrideRepository.save(deleteList);

        return deleteList.get(0).getParticipant().getId();
    }

    @Override
    public Long delete(Long id) {

        List<ParticipantCitizenshipOverride> deleteList =
                participantCitizenshipOverrideRepository
                        .findByParticipantAndAndNexttransactiontimeIsAfter(id,new Timestamp(System.currentTimeMillis()));

        if (deleteList.isEmpty()) return id;
        deleteList = deleteList.stream().peek(item->item.setNexttransactiontime(new Timestamp(System.currentTimeMillis())))
                .collect(Collectors.toList());

        participantCitizenshipOverrideRepository.save(deleteList);

        return deleteList.get(0).getParticipant().getId();
    }
}
