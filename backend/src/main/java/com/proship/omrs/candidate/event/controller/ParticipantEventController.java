package com.proship.omrs.candidate.event.controller;
import com.proship.omrs.candidate.event.param.Note;
import com.proship.omrs.candidate.event.param.ParticipantEventParam;
import com.proship.omrs.candidate.event.service.ParticipantEventService;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/candidate-event/")
public class ParticipantEventController {


    @Autowired
    ParticipantEventService participantEventService;

    @RequestMapping(value = "create/{candidateId}",method = RequestMethod.POST)
    @ApiOperation(value = "Add one or multiple new citizenship info for the given candidate " +
            "and return the created event(note).",
            response=Note.class)
    public ResponseEntity<Note> create(@PathVariable("candidateId")Long id,@Valid @RequestBody ParticipantEventParam event){

        return new ResponseEntity<>(participantEventService.addNewEvent(id,event), HttpStatus.CREATED);
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ApiOperation(value = "Update the note or type of the given event " +
            "and return the updated event(note).",
            response=Note.class)
    public ResponseEntity<Note> update(@Valid@RequestBody ParticipantEventParam event){

        return new ResponseEntity<>(participantEventService.updateEvent(event), HttpStatus.CREATED);
    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    @ApiOperation(value = "Logically delete the given event and return the id of the deleted event id.",
            response=Long.class)
    public ResponseEntity<Long>delete(@PathVariable("id")Long id){
        return new ResponseEntity<>(participantEventService.delete(id),HttpStatus.OK);
    }

    @RequestMapping(value = "candidate/{candidateId}",method = RequestMethod.GET)
    @ApiOperation(value = "Get all the valid events(notes) for given candidate id.",
            response=List.class)
    public ResponseEntity<List<Note>> getByCandidateId(@PathVariable("candidateId")Long id){
        return new ResponseEntity<>(participantEventService.getByCandidateId(id), HttpStatus.OK);
    }

}
