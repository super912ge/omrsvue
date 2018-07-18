package com.proship.omrs.candidate.address.service;

import com.proship.omrs.candidate.address.entity.ParticipantAddress;
import com.proship.omrs.candidate.address.entity.ParticipantAddressOverride;
import com.proship.omrs.candidate.address.param.CreateAddressParam;
import com.proship.omrs.candidate.address.param.UpdateAddressParam;
import com.proship.omrs.candidate.address.repository.ParticipantAddressOverrideRepository;
import com.proship.omrs.candidate.address.repository.ParticipantAddressRepository;
import com.proship.omrs.candidate.base.entity.BaseEntityCountry;
import com.proship.omrs.candidate.base.entity.BaseOverrideEntity;
import com.proship.omrs.candidate.base.service.CandidateBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class AddressServiceImpl extends
        CandidateBaseServiceImpl<ParticipantAddress,ParticipantAddressOverride> implements AddressService {

    @Autowired
    ParticipantAddressRepository participantAddressRepository;

    @Autowired
    ParticipantAddressOverrideRepository participantAddressOverrideRepository;

    @Override
    public Long addAddress(Long id, CreateAddressParam param) {


        ParticipantAddress address = getNewBaseEntity(new ParticipantAddress());

        address.setCountryId(param.getCountry());

        address.setCity(param.getCity());

        address.setLine1(param.getLine1());

        address.setLine2(param.getLine2());

        address.setPostalCode(param.getPostalCode());

        address.setRegion(param.getRegion());

        participantAddressRepository.save(address);

        ParticipantAddressOverride participantAddressOverride =
                getNewBaseOverrideEntity(new ParticipantAddressOverride(),id);

        participantAddressOverride.setParticipantAddress(address);

        participantAddressOverride.setId(address.getId());

        participantAddressOverrideRepository.save(participantAddressOverride);

        return address.getId();

    }

    @Override
    public Long update(UpdateAddressParam param) {

        Long candidate = delete(param.getId());
        return addAddress(candidate,param);
    }

    @Override
    public Long delete(Long id) {

        ParticipantAddressOverride participantAddressOverride = participantAddressOverrideRepository.getOne(id);

        participantAddressOverride.setNexttransactiontime(new Timestamp(System.currentTimeMillis()));

        participantAddressOverrideRepository.save(participantAddressOverride);

        return participantAddressOverride.getParticipant().getId();
    }

}
