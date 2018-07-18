package com.proship.omrs.candidate.homeAirport.service;

import com.proship.omrs.candidate.base.entity.BaseEntity;
import com.proship.omrs.candidate.base.entity.BaseOverrideEntity;
import com.proship.omrs.candidate.base.service.CandidateBaseServiceImpl;
import com.proship.omrs.candidate.homeAirport.entity.ParticipantHomeAirport;
import com.proship.omrs.candidate.homeAirport.entity.ParticipantHomeAirportOverride;
import com.proship.omrs.candidate.homeAirport.param.CreateHomeAirportParam;
import com.proship.omrs.candidate.homeAirport.param.UpdateHomeAirportParam;
import com.proship.omrs.candidate.homeAirport.repository.ParticipantHomeAirportOverrideRepository;
import com.proship.omrs.candidate.homeAirport.repository.ParticipantHomeAirportRepository;
import com.proship.omrs.system.airport.entity.Airport;
import com.proship.omrs.system.airport.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class HomeAirportServiceImpl extends
        CandidateBaseServiceImpl<ParticipantHomeAirport,ParticipantHomeAirportOverride>
        implements HomeAirportService{

    @Autowired
    ParticipantHomeAirportRepository participantHomeAirportRepository;

    @Autowired
    ParticipantHomeAirportOverrideRepository participantHomeAirportOverrideRepository;

    @Autowired
    AirportRepository airportRepository;


    @Override
    public Long create(Long candidateId, CreateHomeAirportParam param) {




            ParticipantHomeAirport participantHomeAirport =
                    getNewBaseEntity(new ParticipantHomeAirport());

            Airport airport = airportRepository.getOne(param.getAirportId());

            participantHomeAirport.setAirport(airport);

            participantHomeAirport.setComment(param.getClient().trim());

            participantHomeAirport = participantHomeAirportRepository.save(participantHomeAirport);

            ParticipantHomeAirportOverride participantHomeAirportOverride =
                   getNewBaseOverrideEntity(new ParticipantHomeAirportOverride(),candidateId);

            participantHomeAirportOverride.setId(participantHomeAirport.getId());

            participantHomeAirportOverride.setParticipantHomeAirport(participantHomeAirport);

            participantHomeAirportOverrideRepository.save(participantHomeAirportOverride);

            return participantHomeAirport.getId();

    }

    @Override
    public Long update(UpdateHomeAirportParam param) {

        ParticipantHomeAirportOverride override = participantHomeAirportOverrideRepository.getOne(param.getId());

        ParticipantHomeAirport airport = override.getParticipantHomeAirport();

        if(airport.getAirport().getId().equals(param.getAirportId())
                && airport.getComment().equals(param.getClient().trim())){
            return airport.getId();
        }

        Long candidate = delete(param.getId());

        return create(candidate,param);

    }

    @Override
    public Long delete(Long id) {

        ParticipantHomeAirportOverride participantHomeAirportOverride =
                participantHomeAirportOverrideRepository.getOne(id);

        participantHomeAirportOverride.setNexttransactiontime(new Timestamp(System.currentTimeMillis()));

        participantHomeAirportOverrideRepository.save(participantHomeAirportOverride);

        return participantHomeAirportOverride.getParticipant().getId();
    }
}
