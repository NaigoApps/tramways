package tramways.dto.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import tramways.inbound.model.Property;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Json2PropertyMapper {

    public static final String ERROR = "Error";
    private final ObjectMapper mapper;

    public Json2PropertyMapper() {
        mapper = new ObjectMapper();
    }

    public Property mapProperty(String json) {
        try {
            return mapper.readValue(json, Property.class);
        } catch (IOException ex) {
            LoggerFactory.getLogger(getClass()).error(ERROR, ex);
            return null;
        }
    }

    public List<Property> mapProperties(String json) {
        try {
            return mapper.readValue(json, new TypeReference<List<Property>>() {
            });
        } catch (IOException ex) {
            LoggerFactory.getLogger(getClass()).error(ERROR, ex);
            return Collections.emptyList();
        }
    }

    public String map(Property prop) {
        try {
            return mapper.writeValueAsString(prop);
        } catch (JsonProcessingException e) {
            LoggerFactory.getLogger(getClass()).error(ERROR, e);
            return null;
        }
    }

    public String map(List<Property> props) {
        try {
            return mapper.writeValueAsString(props);
        } catch (JsonProcessingException e) {
            LoggerFactory.getLogger(getClass()).error(ERROR, e);
            return null;
        }
    }
}
