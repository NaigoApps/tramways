package tramways.dto.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Json2ConfigurationDtoMapper {

	private ObjectMapper mapper;

	public Json2ConfigurationDtoMapper() {
		mapper = new ObjectMapper();
	}

//	public ConfigurationDto map(String json) {
//		try {
//			return mapper.readValue(json, ConfigurationDto.class);
//		} catch (IOException e) {
//			LoggerFactory.getLogger(getClass()).error("Error", e);
//			return null;
//		}
//	}

}
