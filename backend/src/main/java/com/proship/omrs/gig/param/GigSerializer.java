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

        jsonGenerator.writeNumberField("gigTypeId",gig.getShard().getGigType());

        jsonGenerator.writeStringField("gigType", GigTypeMap.getBandType(gig.getShard().getGigType()).getName());

        Room room = gig.getShard().getRoom();

        jsonGenerator.writeNumberField("roomId", room.getId());

        jsonGenerator.writeStringField("roomName",room.getName());

        jsonGenerator.writeNumberField("venueId",room.getVenue());

        jsonGenerator.writeStringField("venueName",
                VenueMainShardMap.getVenueMainShard(room.getVenue()).getName());

        jsonGenerator.writeNumberField("clientId",
                VenueMainShardMap.getVenueMainShard(room.getVenue()).getClientId());

        jsonGenerator.writeStringField("clientName",
                ClientMap.getClient(VenueMainShardMap.getVenueMainShard(room.getVenue()).getClientId()).getName());

        jsonGenerator.writeEndObject();
    }
}
