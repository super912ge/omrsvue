package com.proship.omrs.gig.param;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.proship.omrs.client.entity.ClientMap;
import com.proship.omrs.venue.entity.Room;
import com.proship.omrs.venue.entity.VenueMainShardMap;

import java.io.IOException;

public class RoomSerializer extends StdSerializer<Room> {

    public RoomSerializer(Class<Room> t){
        super(t);
    }
    public RoomSerializer(){
        this(null);
    }
    @Override
    public void serialize(Room room, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id", room.getId());

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
