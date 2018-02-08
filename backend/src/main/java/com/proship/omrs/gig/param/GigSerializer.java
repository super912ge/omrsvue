package com.proship.omrs.gig.param;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.proship.omrs.client.entity.ClientMap;
import com.proship.omrs.gig.entity.Gig;
import com.proship.omrs.gig.entity.GigTypeMap;
import com.proship.omrs.venue.entity.Room;
import com.proship.omrs.venue.entity.VenueMainShardMap;

import java.io.IOException;

public class GigSerializer extends StdSerializer<Gig> {

    private static final long serialVersionUID = 1L;

    public GigSerializer(Class<Gig> t) {
        super(t);
    }
    public GigSerializer() {
        this(null);
    }

    @Override
    public void serialize(Gig gig, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id",gig.getId());

        jsonGenerator.writeObjectFieldStart("gigType");

        jsonGenerator.writeNumberField("id",gig.getShard().getGigType());

        jsonGenerator.writeStringField("name", GigTypeMap.getBandType(gig.getShard().getGigType()).getName());

        jsonGenerator.writeEndObject();

        Room room = gig.getShard().getRoom();

        jsonGenerator.writeObjectFieldStart("room");

        jsonGenerator.writeNumberField("id", room.getId());

        jsonGenerator.writeStringField("name",room.getName());

        jsonGenerator.writeEndObject();

        jsonGenerator.writeObjectFieldStart("venue");
        jsonGenerator.writeNumberField("id",room.getVenue());

        jsonGenerator.writeStringField("name",
                VenueMainShardMap.getVenueMainShard(room.getVenue()).getName());

        jsonGenerator.writeEndObject();

        jsonGenerator.writeObjectFieldStart("client");

        jsonGenerator.writeNumberField("id",
                VenueMainShardMap.getVenueMainShard(room.getVenue()).getClientId());

        jsonGenerator.writeStringField("name",
                ClientMap.getClient(VenueMainShardMap.getVenueMainShard(room.getVenue()).getClientId()).getName());

        jsonGenerator.writeEndObject();

        jsonGenerator.writeEndObject();
    }
}
