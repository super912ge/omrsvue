package com.proship.omrs.candidate.participant.param;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.proship.omrs.candidate.participant.entity.Participant;

import java.io.IOException;

public class ParticipantSerializer extends StdSerializer<Participant> {


    public ParticipantSerializer(Class<Participant>t){
        super(t);
    }

    public ParticipantSerializer(){
        this(null);
    }

    @Override
    public void serialize(Participant participant, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id",participant.getId());
        jsonGenerator.writeNumberField("act_id",participant.getParticipantAct().getId());
        jsonGenerator.writeStringField("name",participant.getName());
        jsonGenerator.writeEndObject();
    }
}
