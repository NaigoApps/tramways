package tramways.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.junit.Test;

import tramways.CDITest;
import tramways.DefaultMessageCollector;
import tramways.Utils;
import tramways.dto.RoadMap;
import tramways.mapper.Json2RoadMapDtoMapper;

public class RoadMapValidatorTest extends CDITest {

	@Inject
	private RoadMapValidator validator;

	@Inject
	private Json2RoadMapDtoMapper mapper;

	@Test
	public void testValidateOk01() {
		RoadMap map = mapper.map(Utils.readJson("json/roads_01.json"));
		validator.setMap(map);
		assertTrue(validator.validate(new DefaultMessageCollector()));
	}

	@Test
	public void testValidateOk02() {
		RoadMap map = mapper.map(Utils.readJson("json/roads_02.json"));
		validator.setMap(map);
		assertTrue(validator.validate(new DefaultMessageCollector()));
	}

	@Test
	public void testValidateOk03() {
		RoadMap map = mapper.map(Utils.readJson("json/roads_03.json"));
		validator.setMap(map);
		assertTrue(validator.validate(new DefaultMessageCollector()));
	}

	@Test
	public void testValidateOk04() {
		RoadMap map = mapper.map(Utils.readJson("json/roads_04.json"));
		validator.setMap(map);
		assertTrue(validator.validate(new DefaultMessageCollector()));
	}

	@Test
	public void testValidateFail01() {
		RoadMap map = mapper.map(Utils.readJson("json/bad_map_01.json"));
		validator.setMap(map);
		assertFalse(validator.validate(new DefaultMessageCollector()));
	}

	@Test
	public void testValidateFail02() {
		RoadMap map = mapper.map(Utils.readJson("json/bad_map_02.json"));
		validator.setMap(map);
		assertFalse(validator.validate(new DefaultMessageCollector()));
	}

}
