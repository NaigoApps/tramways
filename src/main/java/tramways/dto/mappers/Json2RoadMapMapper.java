package tramways.dto.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import tramways.inbound.model.RoadMap;

import java.io.IOException;

public class Json2RoadMapMapper {

	private final ObjectMapper mapper;

	public Json2RoadMapMapper() {
		mapper = new ObjectMapper();
	}

	public RoadMap map(String json) {
		try {
			return mapper.readValue(json, RoadMap.class);
		}catch (IOException ex){
			LoggerFactory.getLogger(getClass()).error("Error", ex);
			return null;
		}
	}

	public String map(RoadMap roadMap) {
		try {
			return mapper.writeValueAsString(roadMap);
		} catch (JsonProcessingException e) {
			LoggerFactory.getLogger(getClass()).error("Error", e);
			return null;
		}
	}
}
