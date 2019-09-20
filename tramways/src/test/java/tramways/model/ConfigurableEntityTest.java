package tramways.model;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;

import tramways.Utils;
import tramways.dto.ConfigurationDto;
import tramways.mapper.ConfigurationMapper;
import tramways.mapper.Json2ConfigurationDtoMapper;

public class ConfigurableEntityTest {

	@Rule
    public WeldInitiator weld = WeldInitiator.from(new Weld()).inject(this).build();
	
	private ConfigurableEntity entity;
	
	@Inject
	private ConfigurationMapper mapper;
	
	@Test
	public void testJson() {
		entity = new ConfigurableEntity();
		Json2ConfigurationDtoMapper jsonMapper = new Json2ConfigurationDtoMapper();
		ConfigurationDto confDto = jsonMapper.map(Utils.readJson("json/configuration_01.json"));
		Configuration conf = mapper.map(confDto);
		
		entity.apply(conf);
		
		assertEquals(0, entity.getProperty("A", Long.class).intValue());
		assertEquals("s", entity.getProperty("B", String.class));
		assertEquals(0.6, entity.getProperty("C", Double.class).doubleValue(), 0.0);
	}

	
}
