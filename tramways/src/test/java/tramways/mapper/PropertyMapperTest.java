package tramways.mapper;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.Test;

import tramways.CDITest;
import tramways.dto.mappers.PropertyMapper;
import tramways.model.persistable.properties.PropertyWrapper;
import tramways.model.properties.IntegerProperty;
import tramways.model.properties.Property;

public class PropertyMapperTest extends CDITest {

	@Inject
	private PropertyMapper mapper;
	
	@Test
	public void testMap() {
		
		IntegerProperty dto = new IntegerProperty();
		dto.setName("name");
		dto.setValue(5L);
		
		PropertyWrapper prop = mapper.map(dto);
		assertEquals("name", prop.getName());
		assertEquals(5L, prop.getValue());

		Property result = mapper.map(prop);
		assertEquals("name", result.getName());
		assertEquals(5L, result.getValue());
	}

}