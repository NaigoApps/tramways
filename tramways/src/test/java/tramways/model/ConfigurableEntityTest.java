package tramways.model;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.Test;

import tramways.CDITest;
import tramways.Utils;
import tramways.dto.ConfigurationDto;
import tramways.mapper.ConfigurationMapper;
import tramways.mapper.Json2ConfigurationDtoMapper;

public class ConfigurableEntityTest extends CDITest{

	private AbstractConfigurable entity;
	
	@Inject
	private ConfigurationMapper mapper;
	
	@Test
	public void testJson() {
		entity = new AbstractConfigurable();
		Json2ConfigurationDtoMapper jsonMapper = new Json2ConfigurationDtoMapper();
		ConfigurationDto confDto = jsonMapper.map(Utils.readJson("json/configuration_01.json"));
		Configuration conf = mapper.map(confDto);
		
		entity.apply(conf);
		
		assertEquals(0, entity.getIntegerProperty("A").intValue());
		assertEquals("s", entity.getStringProperty("B"));
		assertEquals(0.6, entity.getDecimalProperty("C").doubleValue(), 0.0);
	}

	
}
