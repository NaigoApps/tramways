package tramways.dto.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import tramways.core.model.roadmap.RoadMap;

import java.io.IOException;

public class Json2RoadMapDtoMapper {

	private ObjectMapper mapper;

	public Json2RoadMapDtoMapper() {
		mapper = new ObjectMapper();
	}

	public RoadMap map(String json) {
		try {
			RoadMap result = mapper.readValue(json, RoadMap.class);
			result.initialize();
			return result;
		}catch (IOException ex){
			LoggerFactory.getLogger(getClass()).error("Error", ex);
			return null;
		}
	}

}
