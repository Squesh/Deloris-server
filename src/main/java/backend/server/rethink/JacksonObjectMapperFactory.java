package backend.server.rethink;

import backend.server.rethink.serialization.InstantDeserializer;
import backend.server.rethink.serialization.InstantSerializer;
import backend.server.rethink.serialization.UUIDDeserializer;
import backend.server.rethink.serialization.UUIDSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.time.Instant;
import java.util.UUID;

public class JacksonObjectMapperFactory {

    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();

        module.addSerializer(UUID.class, new UUIDSerializer());
        module.addDeserializer(UUID.class, new UUIDDeserializer());

        module.addSerializer(Instant.class, new InstantSerializer());
        module.addDeserializer(Instant.class, new InstantDeserializer());

        return objectMapper;
    }
}