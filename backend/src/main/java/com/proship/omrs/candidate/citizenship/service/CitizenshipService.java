package com.proship.omrs.candidate.citizenship.service;


import com.proship.omrs.candidate.citizenship.entity.ParticipantCitizenship;
import com.proship.omrs.candidate.citizenship.param.UpdateCitizenshipParam;

import java.util.List;

public interface CitizenshipService {


    List<ParticipantCitizenship> addCitizenship(Long id, List<Long> countryId);

    List<ParticipantCitizenship>update(Long id, List<Long>ids);

    Long delete(List<Long> ids);

    Long delete(Long id);

}
