package tramways.services;

import org.junit.Test;
import tramways.CDITest;
import tramways.DefaultMessageCollector;
import tramways.Utils;
import tramways.dto.mappers.Json2RoadMapMapper;
import tramways.inbound.model.RoadMapContent;

import javax.inject.Inject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RoadMapValidatorTest extends CDITest {

	@Inject
	private RoadMapValidator validator;

	@Inject
	private Json2RoadMapMapper mapper;

	@Test
	public void testValidateOk01() {
		RoadMapContent map = mapper.map(Utils.readJson("json/roads_01.json"));
		validator.setMap(map);
		assertTrue(validator.validate(new DefaultMessageCollector()));
	}

	@Test
	public void testValidateOk02() {
		RoadMapContent map = mapper.map(Utils.readJson("json/roads_02.json"));
		validator.setMap(map);
		assertTrue(validator.validate(new DefaultMessageCollector()));
	}

	@Test
	public void testValidateOk03() {
		RoadMapContent map = mapper.map(Utils.readJson("json/roads_03.json"));
		validator.setMap(map);
		assertTrue(validator.validate(new DefaultMessageCollector()));
	}

	@Test
	public void testValidateOk04() {
		RoadMapContent map = mapper.map(Utils.readJson("json/roads_04.json"));
		validator.setMap(map);
		assertTrue(validator.validate(new DefaultMessageCollector()));
	}

	@Test
	public void testValidateFail01() {
		RoadMapContent map = mapper.map(Utils.readJson("json/bad_map_01.json"));
		validator.setMap(map);
		assertFalse(validator.validate(new DefaultMessageCollector()));
	}

	@Test
	public void testValidateFail02() {
		RoadMapContent map = mapper.map(Utils.readJson("json/bad_map_02.json"));
		validator.setMap(map);
		assertFalse(validator.validate(new DefaultMessageCollector()));
	}

}
