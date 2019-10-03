package tramways.mapper;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.Test;

import tramways.CDITest;
import tramways.dto.properties.IntegerPropertyWrapper;
import tramways.dto.properties.PropertyWrapper;
import tramways.model.properties.Property;

public class PropertyMapperTest extends CDITest {

	@Inject
	private PropertyMapper mapper;
	
	@Test
	public void testMap() {
		
		IntegerPropertyWrapper dto = new IntegerPropertyWrapper();
		dto.setName("name");
		dto.setValue(5L);
		
		Property prop = mapper.map(dto);
		assertEquals("name", prop.getName());
		assertEquals(5L, prop.getValue());

		PropertyWrapper result = mapper.map(prop);
		assertEquals("name", result.getName());
		assertEquals(5L, result.getValue());
	}

}
