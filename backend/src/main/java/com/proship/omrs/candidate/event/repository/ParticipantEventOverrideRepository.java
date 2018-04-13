package com.proship.omrs.candidate.event.repository;

import com.proship.omrs.base.repository.CustomizedRepository;
import com.proship.omrs.candidate.event.entity.ParticipantEventOverride;

import java.util.List;

public interface ParticipantEventOverrideRepository extends CustomizedRepository<ParticipantEventOverride,Long>{

    List<ParticipantEventOverride> findAllByParticipantId(Long id);
}
