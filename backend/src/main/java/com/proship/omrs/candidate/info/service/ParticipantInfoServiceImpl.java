package com.proship.omrs.candidate.info.service;

import com.proship.omrs.candidate.base.service.CandidateBaseServiceImpl;
import com.proship.omrs.candidate.info.entity.ParticipantInfoOverride;
import com.proship.omrs.candidate.info.entity.ParticipantInfoTemporal;
import com.proship.omrs.candidate.info.param.CreateInfoParam;
import com.proship.omrs.candidate.info.repository.ParticipantInfoOverrideRepository;
import com.proship.omrs.candidate.info.repository.ParticipantInfoTemporalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipantInfoServiceImpl extends CandidateBaseServiceImpl implements ParticipantInfoService{

    @Autowired
    ParticipantInfoTemporalRepository participantInfoTemporalRepository;

    @Autowired
    ParticipantInfoOverrideRepository participantInfoOverrideRepository;

    @Override
    public Long addNewInfo(Long id, CreateInfoParam param) {

        ParticipantInfoTemporal participantInfoTemporal = (ParticipantInfoTemporal)getNewBaseEntity();

        participantInfoTemporal.setParticipantinfotype(param.getType());

        participantInfoTemporal.setParticipantinfovalue(param.getValue());

        participantInfoTemporalRepository.save(participantInfoTemporal);

        ParticipantInfoOverride participantInfoOverride = (ParticipantInfoOverride) getNewBaseOverrideEntity(id);

        participantInfoOverride.setId(participantInfoTemporal.getId());

        participantInfoOverride.setParticipantInfoTemporal(participantInfoTemporal);
        participantInfoOverrideRepository.save(participantInfoOverride);

        return participantInfoOverride.getId();
    }
}
