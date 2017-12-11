package com.proship.omrs.candidate.candidate.param;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.proship.omrs.evaluation.entity.EvalTag;

import java.io.IOException;

public class EvalTagSerializer extends StdSerializer<EvalTag> {
    public EvalTagSerializer(Class<EvalTag> t) {
        super(t);
    }
    public EvalTagSerializer(){
        this(null);
    }

    @Override
    public void serialize(EvalTag evalTag, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("label",evalTag.getType().getLabel());
//        jsonGenerator.writeString("children",);
        jsonGenerator.writeEndObject();
    }
}
