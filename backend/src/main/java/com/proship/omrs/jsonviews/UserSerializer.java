package com.proship.omrs.jsonviews;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.proship.omrs.user.entity.User;


public class UserSerializer extends StdSerializer<User> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserSerializer(Class<User> t) {
        super(t);
    }
	public UserSerializer() {
        this(null);
    }

    @Override
    public void serialize(User user, 
                          JsonGenerator jgen,
                          SerializerProvider sp) throws IOException, JsonGenerationException {
        
        jgen.writeStartObject();      
        jgen.writeNumberField("id", user.getId());
        jgen.writeStringField("name", user.getName());      
        
        jgen.writeEndObject();
    }
}
