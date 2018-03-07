package com.proship.omrs.candidate.contact.service;

import com.proship.omrs.candidate.base.service.CandidateBaseServiceImpl;
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
public class ContactServiceImpl extends CandidateBaseServiceImpl implements ContactService {

    @Autowired
    ParticipantContactFieldOverrideRepository participantContactFieldOverrideRepository;

    @Autowired
    ParticipantContactFieldRepository participantContactFieldRepository;


    @Override
    public Long create(Long candidateId, CreateContactParam param) {

        ParticipantContactField participantContactField =(ParticipantContactField) getNewBaseEntity();

        participantContactField.setContactFieldLabelId(param.getType());

        participantContactField.setContactFieldTypeId(param.getMethod());

        participantContactField.setValue(param.getText());

        participantContactField.setTransactiontime(new Timestamp(System.currentTimeMillis()));

        participantContactField = participantContactFieldRepository.save(participantContactField);

        ParticipantContactFieldOverride participantContactFieldOverride =
                (ParticipantContactFieldOverride) getNewBaseOverrideEntity(candidateId);

        participantContactFieldOverride.setId(participantContactField.getId());

        participantContactFieldOverride.setParticipantContactField(participantContactField);

        participantContactFieldOverrideRepository.save(participantContactFieldOverride);

        return participantContactField.getId();

    }
}
