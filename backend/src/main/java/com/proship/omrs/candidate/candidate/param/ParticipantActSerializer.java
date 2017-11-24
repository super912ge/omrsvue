package com.proship.omrs.candidate.candidate.param;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.proship.omrs.candidate.candidate.entity.Participant;
import com.proship.omrs.candidate.candidate.entity.ParticipantAct;

import java.io.IOException;

public class ParticipantActSerializer extends StdSerializer<ParticipantAct>{


    public ParticipantActSerializer(Class<ParticipantAct>t){
        super(t);
    }

    public ParticipantActSerializer(){
        this(null);
    }

    @Override
    public void serialize(ParticipantAct participantAct, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id",participantAct.getParticipant().getId());
        jsonGenerator.writeStringField("name",participantAct.getParticipant().getName());
        jsonGenerator.writeNumberField("participantActId", participantAct.getId());
        jsonGenerator.writeEndObject();
    }
}
