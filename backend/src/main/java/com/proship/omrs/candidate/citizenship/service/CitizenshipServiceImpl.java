package com.proship.omrs.candidate.citizenship.service;

import com.proship.omrs.candidate.base.service.CandidateBaseServiceImpl;
import com.proship.omrs.candidate.citizenship.entity.ParticipantCitizenshipOverride;
import com.proship.omrs.candidate.citizenship.repository.ParticipantCitizenshipRepository;
import com.proship.omrs.candidate.citizenship.entity.ParticipantCitizenship;
import com.proship.omrs.candidate.citizenship.repository.ParticipantCitizenshipOverrideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CitizenshipServiceImpl extends CandidateBaseServiceImpl implements CitizenshipService{

    @Autowired
    ParticipantCitizenshipOverrideRepository participantCitizenshipOverrideRepository;

    @Autowired
    ParticipantCitizenshipRepository participantCitizenshipRepository;


    @Override
    public List<Long> addCitizenship(Long id, List<Long> countryIds) {


        List<Long> ids = new ArrayList<>();

        for (Long countryId: countryIds){

            ParticipantCitizenship participantCitizenship =
                    (ParticipantCitizenship) getNewBaseEntityWithCountryId(countryId);

            participantCitizenship = participantCitizenshipRepository.save(participantCitizenship);

            ParticipantCitizenshipOverride participantCitizenshipOverride =
                    (ParticipantCitizenshipOverride) getNewBaseOverrideEntity(id);

            participantCitizenshipOverride.setId(participantCitizenship.getId());

            participantCitizenshipOverride.setParticipantCitizenship(participantCitizenship);

            participantCitizenshipOverrideRepository.save(participantCitizenshipOverride);

            ids.add(participantCitizenship.getId());

        }

        return ids;
    }
}
