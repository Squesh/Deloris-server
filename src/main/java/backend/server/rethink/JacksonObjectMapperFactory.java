package backend.server.rethink;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JacksonObjectMapperFactory {

    private final Map<Class, JsonSerializer> serializers;
    private final Map<Class, JsonDeserializer> deserializers;
    private final List<Module> modules;

    public JacksonObjectMapperFactory() {
        this(null, null, null);
    }

    public JacksonObjectMapperFactory(List<Module> modules, Map<Class, JsonSerializer> serializers,
                                      Map<Class, JsonDeserializer> deserializers) {

        this.serializers = serializers == null ? new HashMap<>() : serializers;
        this.deserializers = deserializers == null ? new HashMap<>() : deserializers;
        this.modules = modules == null ? Collections.emptyList() : modules;
    }

    public JacksonObjectMapperFactory(List<Module> modules) {
        this(modules, null, null);
    }

    public JacksonObjectMapperFactory(Map<Class, JsonSerializer> serializers,
                                      Map<Class, JsonDeserializer> deserializers) {

        this(Collections.emptyList(), serializers, deserializers);
    }

    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModules(modules);
        if (!serializers.isEmpty() || deserializers.isEmpty()) {
            SimpleModule module = new SimpleModule();
            serializers.entrySet().forEach(entry -> module.addSerializer(entry.getKey(), entry.getValue()));
            deserializers.entrySet().forEach(entry -> module.addDeserializer(entry.getKey(), entry.getValue()));
        }
        return objectMapper;
    }
}