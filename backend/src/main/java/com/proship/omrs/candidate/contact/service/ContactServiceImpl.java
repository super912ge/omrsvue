package com.proship.omrs.candidate.contact.service;

import com.proship.omrs.candidate.base.entity.BaseEntity;
import com.proship.omrs.candidate.base.entity.BaseOverrideEntity;
import com.proship.omrs.candidate.base.service.CandidateBaseServiceImpl;
import com.proship.omrs.candidate.contact.param.UpdateContactParam;
import com.proship.omrs.candidate.participant.entity.Participant;
import com.proship.omrs.candidate.participant.repository.ParticipantRepository;
import com.proship.omrs.candidate.contact.entity.ParticipantContactField;
import com.proship.omrs.candidate.contact.entity.ParticipantContactFieldOverride;
import com.proship.omrs.candidate.contact.param.CreateContactParam;
import com.proship.omrs.candidate.contact.repository.ParticipantContactFieldOverrideRepository;
import com.proship.omrs.candidate.contact.repository.ParticipantContactFieldRepository;
import com.proship.omrs.user.entity.CustomUser;
import com.proship.omrs.utils.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
@Service
public class ContactServiceImpl extends CandidateBaseServiceImpl
        <ParticipantContactField,ParticipantContactFieldOverride> implements ContactService {

    @Autowired
    ParticipantContactFieldOverrideRepository participantContactFieldOverrideRepository;

    @Autowired
    ParticipantContactFieldRepository participantContactFieldRepository;


    @Override
    public Long create(Long candidateId, CreateContactParam param) {

        ParticipantContactField participantContactField = getNewBaseEntity(new ParticipantContactField());

        ParticipantContactFieldOverride participantContactFieldOverride =
                getNewBaseOverrideEntity(new ParticipantContactFieldOverride(),candidateId);

            participantContactField.setContactFieldLabelId(param.getType());

            participantContactField.setContactFieldTypeId(param.getMethod());

            participantContactField.setValue(param.getText());

            participantContactField.setTransactiontime(new Timestamp(System.currentTimeMillis()));

            participantContactField = participantContactFieldRepository.save(participantContactField);

            participantContactFieldOverride.setId(participantContactField.getId());

            participantContactFieldOverride.setParticipantContactField(participantContactField);

            participantContactFieldOverrideRepository.save(participantContactFieldOverride);

            return participantContactField.getId();

    }

    @Override
    public Long update(UpdateContactParam param) {

        Long candidateId = delete(param.getId());

        return create(candidateId,param);
    }

    @Override
    public Long delete(Long id) {
        ParticipantContactFieldOverride participantContactFieldOverride
                = participantContactFieldOverrideRepository.findOne(id);

        participantContactFieldOverride.setNexttransactiontime(new Timestamp(System.currentTimeMillis()));

        participantContactFieldOverrideRepository.save(participantContactFieldOverride);

        return participantContactFieldOverride.getParticipant().getId();

    }
}
