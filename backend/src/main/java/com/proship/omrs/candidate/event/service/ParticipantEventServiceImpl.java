package com.proship.omrs.candidate.event.service;
import com.proship.omrs.candidate.event.entity.*;
import com.proship.omrs.candidate.event.param.Note;
import com.proship.omrs.candidate.event.param.ParticipantEventParam;
import com.proship.omrs.candidate.event.repository.*;
import com.proship.omrs.candidate.participant.entity.Participant;
import com.proship.omrs.candidate.participant.repository.ParticipantRepository;
import com.proship.omrs.exceptions.customExceptions.CandidateNotFoundException;
import com.proship.omrs.exceptions.customExceptions.OmrsResourceNotFoundException;
import com.proship.omrs.user.entity.User;
import com.proship.omrs.user.repository.UserRepository;
import com.proship.omrs.utils.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParticipantEventServiceImpl implements ParticipantEventService{

    @Autowired
    ParticipantEventRepository eventRepository;

    @Autowired
    ParticipantEventCommentRepository commentRepository;

    @Autowired
    ParticipantRepository participantRepository;

    @Autowired
    ParticipantEventTypeTtsRepository eventTypeRepository;

    @Autowired
    ParticipantEventTemporalRepository temporalRepository;

    @Autowired
    ParticipantEventOverrideRepository overrideRepository;

    @Autowired
    UserRepository userRepository;

    private User user;

    private User getUser(){

        if (user==null) {

            String username =
                    (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

           this.user =   userRepository.findByName(username);
        }
       return this.user;
    }

    @Override
    public Note addNewEvent(Long id, ParticipantEventParam param) {

            Participant participant = participantRepository.findById(id).orElse(null);

            ParticipantEventTemporal temporal = new ParticipantEventTemporal();

            ParticipantEventOverride override = new ParticipantEventOverride();

            if (participant==null) throw new CandidateNotFoundException(id);

            ParticipantEvent event = new ParticipantEvent();

            event = eventRepository.save(event);

            ParticipantEventCommentTts commentTts = new ParticipantEventCommentTts();

            commentTts.setText(param.getText());

            commentTts.setComment(event);

            commentTts.setCreatorId(getUser().getId());

            commentTts.setNexttransactiontime(Utils.getInfiniteTimestamp());

            commentTts.setTransactiontime(new Timestamp(System.currentTimeMillis()));

            commentRepository.save(commentTts);

            ParticipantEventTypeTts typeTts = new ParticipantEventTypeTts();

            typeTts.setCreatorId(getUser().getId());

            typeTts.setNexttransactiontime(Utils.getInfiniteTimestamp());

            typeTts.setParticipantEvent(event);

            typeTts.setTransactiontime(new Timestamp(System.currentTimeMillis()));

            typeTts.setParticipantEventTypeId(param.getType());

            eventTypeRepository.saveAndFlush(typeTts);

            eventRepository.refresh(event);

            temporal.setCreator(getUser());

            temporal.setParticipantEvent(event);

            temporal.setTransactiontime(new Timestamp(System.currentTimeMillis()));

            temporal = temporalRepository.save(temporal);

            override.setParticipant(participant);

            override.setId(temporal.getId());

            override.setNexttransactiontime(Utils.getInfiniteTimestamp());

            override.setParticipantEventTemporal(temporal);

            override.setDestroyerId(getUser().getId());

            override = overrideRepository.saveAndFlush(override);

//            overrideRepository.refresh(override);

        Note note =  new Note(override);

        note.setUsername(user.getName());

        return note;
    }

    @Override
    public Note updateEvent(ParticipantEventParam param) {

        ParticipantEventOverride override = overrideRepository.findById(param.getId())
                .orElseThrow(()->new OmrsResourceNotFoundException("ParticipantEventOverride "+param.getId()+" not found."));

        ParticipantEvent event = override.getParticipantEventTemporal().getParticipantEvent();

        if (param.getText()!=null){

            if (param.getText().trim().length()<=1){

                throw new IllegalArgumentException("Candidate event "
                        +param.getId()+": the length of note is too short as \'"+param.getText()+"\'");
            }
            if (!param.getText().trim().equals(event.getParticipantEventCommentTts().getText())){

                ParticipantEventCommentTts eventCommentTts = event.getParticipantEventCommentTts();

                eventCommentTts.setNexttransactiontime(new Timestamp(System.currentTimeMillis()));

                commentRepository.save(eventCommentTts);

                ParticipantEventCommentTts eventCommentTtsUpdated = new ParticipantEventCommentTts();

                eventCommentTtsUpdated.setNexttransactiontime(Utils.getInfiniteTimestamp());

                eventCommentTtsUpdated.setCreatorId(getUser().getId());

                eventCommentTtsUpdated.setTransactiontime(new Timestamp(System.currentTimeMillis()));

                eventCommentTtsUpdated.setComment(event);

                eventCommentTtsUpdated.setText(param.getText());

                commentRepository.saveAndFlush(eventCommentTtsUpdated);

            }
        }
        if(param.getType()!=null){

            if (!event.getParticipantEventTypeTts().getParticipantEventTypeId().equals(param.getType())) {

                ParticipantEventTypeTts typeTts = event.getParticipantEventTypeTts();

                typeTts.setNexttransactiontime(new Timestamp(System.currentTimeMillis()));

                ParticipantEventTypeTts typeTtsUpdated = new ParticipantEventTypeTts();

                typeTtsUpdated.setParticipantEventTypeId(param.getType());

                typeTtsUpdated.setNexttransactiontime(Utils.getInfiniteTimestamp());

                typeTtsUpdated.setTransactiontime(new Timestamp(System.currentTimeMillis()));

                typeTtsUpdated.setCreatorId(getUser().getId());

                typeTtsUpdated.setParticipantEvent(event);

                eventTypeRepository.save(typeTts);

                eventTypeRepository.saveAndFlush(typeTtsUpdated);
            }
            eventRepository.refresh(event);

            override = overrideRepository.findById(param.getId())
                    .orElseThrow(()->new OmrsResourceNotFoundException("ParticipantEventOverride "+param.getId()+" not found."));
        }

        return new Note(override);
    }

    @Override
    public Long delete(Long id) {

        ParticipantEventOverride override = overrideRepository.getOne(id);

        ParticipantEventTemporal temporal = override.getParticipantEventTemporal();

        override.setNexttransactiontime(new Timestamp(System.currentTimeMillis()));

        ParticipantEvent event = temporal.getParticipantEvent();

        event.getParticipantEventCommentTts().setNexttransactiontime(new Timestamp(System.currentTimeMillis()));

        event.getParticipantEventTypeTts().setNexttransactiontime(new Timestamp(System.currentTimeMillis()));

        eventRepository.save(event);

        overrideRepository.save(override);

        return override.getId();
    }

    @Override
    public List<Note> getByCandidateId(Long id) {

        List<ParticipantEventOverride> list = overrideRepository.findAllByParticipantId(id);

        return list.stream().filter(item->item.getNexttransactiontime()
                .after(new Date())).map(Note::new).collect(Collectors.toList());

    }
}
