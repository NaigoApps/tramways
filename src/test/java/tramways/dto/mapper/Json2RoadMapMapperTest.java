package tramways.dto.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static tramways.Utils.readJson;

import org.junit.Before;
import org.junit.Test;

import tramways.core.model.roadmap.lanes.LaneSegment;
import tramways.core.model.roadmap.points.DestinationPoint;
import tramways.core.model.roadmap.points.LaneSegmentLink;
import tramways.core.model.roadmap.points.SourcePoint;
import tramways.core.model.roadmap.points.SourcePointType;
import tramways.core.model.roadmap.points.trafficlight.SensorTrafficLight;
import tramways.core.model.roadmap.points.trafficlight.TrafficLightCrossingPoint;
import tramways.dto.mappers.Json2RoadMapMapper;
import tramways.inbound.model.RoadMap;
import tramways.inbound.model.RoadMapContent;

public class Json2RoadMapMapperTest {

	private Json2RoadMapMapper mapper;

	@Before
	public void setUp() {
		mapper = new Json2RoadMapMapper();
	}

	@Test
	public void testMap01() {
		RoadMapContent dto = mapper.map(readJson("json/roads_01.json"));
		assertTrue(dto.getLanes().isEmpty());
		assertTrue(dto.getPoints().isEmpty());
	}

	@Test
	public void testMap02() {
		RoadMapContent dto = mapper.map(readJson("json/roads_02.json"));
		assertEquals(1, dto.getLanes().size());
		assertEquals(2, dto.getPoints().size());
//		SourcePoint s = (SourcePoint) dto.getPoints().get(0);
//		LaneSegment l = (LaneSegment) dto.getLanes().get(0);
//		DestinationPoint d = (DestinationPoint) dto.getPoints().get(1);
//
//		assertEquals(SourcePointType.CAR, s.getKind());
//		assertTrue(l.getSource().equals(s.getUuid()));
//		assertTrue(l.getDestination().equals(d.getUuid()));
//
//		assertEquals(l.getUuid(), s.getTargetLane());
//
//		assertEquals(1, d.getLanes().size());
//		assertEquals(l.getUuid(), d.getLanes().get(0));
	}

	@Test
	public void testMap03() {
		RoadMapContent dto = mapper.map(readJson("json/roads_03.json"));
		assertEquals(1, dto.getLanes().size());
		assertEquals(2, dto.getPoints().size());
//		SourcePoint s = (SourcePoint) dto.getPoints().get(0);
//		LaneSegment l = (LaneSegment) dto.getLanes().get(0);
//		DestinationPoint d = (DestinationPoint) dto.getPoints().get(1);
//
//		assertEquals(SourcePointType.TRAM, s.getKind());
//		assertTrue(l.getSource().equals(s.getUuid()));
//		assertTrue(l.getDestination().equals(d.getUuid()));
//
//		assertEquals(l.getUuid(), s.getTargetLane());
//
//		assertEquals(1, d.getLanes().size());
//		assertEquals(l.getUuid(), d.getLanes().get(0));
	}

	@Test
	public void testMap04() {
		RoadMapContent map = mapper.map(readJson("json/roads_04.json"));
		assertEquals(4, map.getLanes().size());
		assertEquals(5, map.getPoints().size());

//		SourcePoint cs = map.getPoint("c_S", SourcePoint.class);
//		DestinationPoint cd = map.getPoint("c_D", DestinationPoint.class);
//		SourcePoint ts = map.getPoint("t_S", SourcePoint.class);
//		DestinationPoint td = map.getPoint("t_D", DestinationPoint.class);
//		TrafficLightCrossingPoint cp = map.getPoint("cp", TrafficLightCrossingPoint.class);
//
//		assertEquals(SourcePointType.CAR, cs.getKind());
//		assertEquals(SourcePointType.TRAM, ts.getKind());
//
//		LaneSegment cl1 = map.getLane("cl_A");
//		LaneSegment cl2 = map.getLane("cl_B");
//		LaneSegment tl1 = map.getLane("tl_A");
//		LaneSegment tl2 = map.getLane("tl_B");
//
//		assertEquals(cl1.getUuid(), cs.getTargetLane());
//		assertEquals(tl1.getUuid(), ts.getTargetLane());
//
//		assertEquals(cp.getUuid(), cl1.getDestination());
//		assertEquals(cp.getUuid(), tl1.getDestination());
//		assertEquals(cp.getUuid(), cl2.getSource());
//		assertEquals(cp.getUuid(), tl2.getSource());
//
//		assertEquals(cd.getUuid(), cl2.getDestination());
//		assertEquals(td.getUuid(), tl2.getDestination());
//
//		assertEquals(1, cp.getConstraints().get(tl1.getUuid()).size());
//		LaneSegmentLink tramLink = cp.getConstraints().get(tl1.getUuid()).iterator().next();
//		assertEquals(tl2.getUuid(), tramLink.getDestination());
//		assertEquals(tl2.getUuid(), tramLink.getDestination());
//
//		assertEquals(1, cp.getConstraints().get(cl1.getUuid()).size());
//		LaneSegmentLink carLink = cp.getConstraints().get(cl1.getUuid()).iterator().next();
//		assertEquals(cl2.getUuid(), carLink.getDestination());
//
//		assertEquals(1, cp.getTrafficLights().size());
//		SensorTrafficLight trafficLight = (SensorTrafficLight) cp.getTrafficLights().get(cl1.getUuid());
//		assertEquals(1, trafficLight.getActivators().size());
//		assertEquals(tl1.getUuid(), trafficLight.getActivators().iterator().next());

	}

}
