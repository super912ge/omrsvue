package com.proship.omrs.candidate.participant.controller;

import com.proship.omrs.base.controller.BaseController;
import com.proship.omrs.candidate.event.entity.ParticipantEventType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/participantEvent/")
public class ParticipantEventTypeController extends BaseController<ParticipantEventType,Long>{
    public ParticipantEventTypeController(CrudRepository<ParticipantEventType, Long> repo) {
        super(repo);
    }
}
