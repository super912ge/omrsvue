package com.proship.omrs.candidate.residency.service;

import com.proship.omrs.candidate.base.service.CandidateBaseServiceImpl;
import com.proship.omrs.candidate.participant.entity.Participant;
import com.proship.omrs.candidate.residency.entity.ParticipantResidency;
import com.proship.omrs.candidate.residency.entity.ParticipantResidencyOverride;
import com.proship.omrs.candidate.residency.repository.ParticipantResidencyOverrideRepository;
import com.proship.omrs.candidate.residency.repository.ParticipantResidencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResidencyServiceImpl extends CandidateBaseServiceImpl implements ResidencyService {

    @Autowired
    ParticipantResidencyRepository participantResidencyRepository;

    @Autowired
    ParticipantResidencyOverrideRepository participantResidencyOverrideRepository;
    @Override
    public List<Long> addResidency(Long id, List<Long> countryIds) {

        List<Long> result = new ArrayList<>();

        for (Long countryId :countryIds){

            ParticipantResidency participantResidency = (ParticipantResidency) getNewBaseEntityWithCountryId(countryId);

            participantResidency = participantResidencyRepository.save(participantResidency);

            ParticipantResidencyOverride participantResidencyOverride =
                    (ParticipantResidencyOverride) getNewBaseOverrideEntity(id);

            participantResidencyOverride.setId(participantResidency.getId());

            participantResidencyOverride.setParticipantResidency(participantResidency);

            participantResidencyOverrideRepository.save(participantResidencyOverride);

            result.add(participantResidency.getId());

        }

        return result;
    }
}
