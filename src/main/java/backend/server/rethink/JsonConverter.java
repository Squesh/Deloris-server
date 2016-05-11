package backend.server.rethink;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class JsonConverter<T, ID> {
    private ObjectMapper objectMapper;

    public JsonConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Deprecated
    public JsonConverter() {
        objectMapper = new JacksonObjectMapperFactory().getObjectMapper();
    }

    public T convertMapToEntity(Map<String, Object> map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }

    public String convertValueToJsonString(T entity) throws JsonProcessingException {
        return objectMapper.writeValueAsString(entity);
    }

    public String convertIDToJsonString(ID id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(id);
    }
}