package com.proship.omrs.candidate.event.service;

import com.proship.omrs.candidate.event.param.Note;
import com.proship.omrs.candidate.event.param.ParticipantEventParam;

import java.util.List;

public interface ParticipantEventService {

    Note addNewEvent(Long id,ParticipantEventParam participantEventParam);

    Note updateEvent(ParticipantEventParam participantEventParam);

    Long delete(Long id);

    List<Note> getByCandidateId(Long id);
}
