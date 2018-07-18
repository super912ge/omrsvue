package com.proship.omrs.candidate.info.service;

import com.proship.omrs.candidate.base.entity.BaseEntity;
import com.proship.omrs.candidate.base.entity.BaseOverrideEntity;
import com.proship.omrs.candidate.base.service.CandidateBaseServiceImpl;
import com.proship.omrs.candidate.info.entity.ParticipantInfoOverride;
import com.proship.omrs.candidate.info.entity.ParticipantInfoTemporal;
import com.proship.omrs.candidate.info.param.CreateInfoParam;
import com.proship.omrs.candidate.info.param.UpdateInfoParam;
import com.proship.omrs.candidate.info.repository.ParticipantInfoOverrideRepository;
import com.proship.omrs.candidate.info.repository.ParticipantInfoTemporalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ParticipantInfoServiceImpl extends CandidateBaseServiceImpl<ParticipantInfoTemporal,ParticipantInfoOverride>
        implements ParticipantInfoService{

    @Autowired
    ParticipantInfoTemporalRepository participantInfoTemporalRepository;

    @Autowired
    ParticipantInfoOverrideRepository participantInfoOverrideRepository;

    @Override
    public Long addNewInfo(Long id, CreateInfoParam param) {



            ParticipantInfoTemporal participantInfoTemporal = getNewBaseEntity(new ParticipantInfoTemporal());

                    participantInfoTemporal.setParticipantinfotype(param.getType());

            participantInfoTemporal.setParticipantinfovalue(param.getValue());

            participantInfoTemporalRepository.save(participantInfoTemporal);

            ParticipantInfoOverride participantInfoOverride = getNewBaseOverrideEntity(new ParticipantInfoOverride(),id);

            participantInfoOverride.setId(participantInfoTemporal.getId());

            participantInfoOverride.setParticipantInfoTemporal(participantInfoTemporal);
            participantInfoOverrideRepository.save(participantInfoOverride);

            return participantInfoOverride.getId();
    }

    @Override
    public Long update(UpdateInfoParam param) {

        Long candidate = delete(param.getId());

        return addNewInfo(candidate,param);

    }

    @Override
    public Long delete(Long id) {

        ParticipantInfoOverride participantInfoOverride = participantInfoOverrideRepository.getOne(id);
        participantInfoOverride.setNexttransactiontime(new Timestamp(System.currentTimeMillis()));
        participantInfoOverrideRepository.save(participantInfoOverride);

        return participantInfoOverride.getParticipant().getId();
    }
}
