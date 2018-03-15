package com.proship.omrs.candidate.residency.service;


import com.proship.omrs.candidate.citizenship.param.UpdateCitizenshipParam;
import com.proship.omrs.candidate.residency.entity.ParticipantResidency;

import java.util.List;

public interface ResidencyService {

    List<ParticipantResidency> addResidency(Long id, List<Long> countryIds);

    List<ParticipantResidency> update(Long candidateId, List<Long> id);

    Long delete(List<Long> ids);

    Long delete(Long id);
}
