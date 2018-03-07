package com.proship.omrs.candidate.participant.repository;

import com.proship.omrs.candidate.event.entity.ParticipantEventType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantEventTypeRepository extends JpaRepository<ParticipantEventType,Long>{
}
