package tramways.dto.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static tramways.Utils.readJson;

import org.junit.Before;
import org.junit.Test;

import tramways.dto.mappers.Json2RoadMapDtoMapper;
import tramways.model.distributions.ExponentialDistribution;
import tramways.model.roadmap.RoadMap;
import tramways.model.roadmap.lanes.LaneSegment;
import tramways.model.roadmap.points.DestinationPoint;
import tramways.model.roadmap.points.LaneSegmentLink;
import tramways.model.roadmap.points.SourcePoint;
import tramways.model.roadmap.points.SourcePointType;
import tramways.model.roadmap.points.trafficlight.SensorTrafficLight;
import tramways.model.roadmap.points.trafficlight.TrafficLightCrossingPoint;

public class Json2RoadMapDtoMapperTest {

	private Json2RoadMapDtoMapper mapper;

	@Before
	public void setUp() {
		mapper = new Json2RoadMapDtoMapper();
	}

	@Test
	public void testMap01() {
		RoadMap dto = mapper.map(readJson("json/roads_01.json"));
		assertTrue(dto.getLanes().isEmpty());
		assertTrue(dto.getPoints().isEmpty());
	}

	@Test
	public void testMap02() {
		RoadMap dto = mapper.map(readJson("json/roads_02.json"));
		assertEquals(1, dto.getLanes().size());
		assertEquals(2, dto.getPoints().size());
		SourcePoint s = (SourcePoint) dto.getPoints().get(0);
		LaneSegment l = (LaneSegment) dto.getLanes().get(0);
		DestinationPoint d = (DestinationPoint) dto.getPoints().get(1);

		assertEquals(SourcePointType.CAR, s.getKind());
		assertTrue(l.getSource().equals(s.getUuid()));
		assertTrue(l.getDestination().equals(d.getUuid()));
		assertEquals(1.0d, l.getDecimalProperty("length").doubleValue(), 0.0d);
		assertEquals(2.0d, l.getDecimalProperty("vehicleMaxSpeed").doubleValue(), 0.0d);
		assertEquals(3.0d, l.getDecimalProperty("vehicleMinGap").doubleValue(), 0.0d);
		assertEquals(4.0d, l.getDecimalProperty("vehicleLength").doubleValue(), 0.0d);

		assertEquals(l.getUuid(), s.getTargetLane());
		ExponentialDistribution arrivalRate = (ExponentialDistribution) s.getDistributionProperty("arrivalRate");
		assertEquals(1.0d, arrivalRate.getMean(), 0.0d);

		assertEquals(1, d.getLanes().size());
		assertEquals(l.getUuid(), d.getLanes().get(0));
	}

	@Test
	public void testMap03() {
		RoadMap dto = mapper.map(readJson("json/roads_03.json"));
		assertEquals(1, dto.getLanes().size());
		assertEquals(2, dto.getPoints().size());
		SourcePoint s = (SourcePoint) dto.getPoints().get(0);
		LaneSegment l = (LaneSegment) dto.getLanes().get(0);
		DestinationPoint d = (DestinationPoint) dto.getPoints().get(1);

		assertEquals(SourcePointType.TRAM, s.getKind());
		assertTrue(l.getSource().equals(s.getUuid()));
		assertTrue(l.getDestination().equals(d.getUuid()));
		assertEquals(1.0d, l.getDecimalProperty("length").doubleValue(), 0.0d);
		assertEquals(2.0d, l.getDecimalProperty("vehicleMaxSpeed").doubleValue(), 0.0d);
		assertEquals(3.0d, l.getDecimalProperty("vehicleMinGap").doubleValue(), 0.0d);
		assertEquals(4.0d, l.getDecimalProperty("vehicleLength").doubleValue(), 0.0d);

		assertEquals(5, s.getIntegerProperty("period").intValue());
		assertEquals(l.getUuid(), s.getTargetLane());

		assertEquals(1, d.getLanes().size());
		assertEquals(l.getUuid(), d.getLanes().get(0));
	}

	@Test
	public void testMap04() {
		RoadMap map = mapper.map(readJson("json/roads_04.json"));
		map.initialize();
		assertEquals(4, map.getLanes().size());
		assertEquals(5, map.getPoints().size());

		SourcePoint cs = map.getPoint("c_S", SourcePoint.class);
		DestinationPoint cd = map.getPoint("c_D", DestinationPoint.class);
		SourcePoint ts = map.getPoint("t_S", SourcePoint.class);
		DestinationPoint td = map.getPoint("t_D", DestinationPoint.class);
		TrafficLightCrossingPoint cp = map.getPoint("cp", TrafficLightCrossingPoint.class);

		assertEquals(SourcePointType.CAR, cs.getKind());
		assertEquals(SourcePointType.TRAM, ts.getKind());

		LaneSegment cl1 = map.getLane("cl_A");
		LaneSegment cl2 = map.getLane("cl_B");
		LaneSegment tl1 = map.getLane("tl_A");
		LaneSegment tl2 = map.getLane("tl_B");

		assertEquals(cl1.getUuid(), cs.getTargetLane());
		assertEquals(tl1.getUuid(), ts.getTargetLane());

		assertEquals(cp.getUuid(), cl1.getDestination());
		assertEquals(cp.getUuid(), tl1.getDestination());
		assertEquals(cp.getUuid(), cl2.getSource());
		assertEquals(cp.getUuid(), tl2.getSource());

		assertEquals(cd.getUuid(), cl2.getDestination());
		assertEquals(td.getUuid(), tl2.getDestination());

		assertEquals(1, cp.getConstraints().get(tl1.getUuid()).size());
		LaneSegmentLink tramLink = cp.getConstraints().get(tl1.getUuid()).iterator().next();
		assertEquals(tl2.getUuid(), tramLink.getDestination());
		assertEquals(tl2.getUuid(), tramLink.getDestination());

		assertEquals(1, cp.getConstraints().get(cl1.getUuid()).size());
		LaneSegmentLink carLink = cp.getConstraints().get(cl1.getUuid()).iterator().next();
		assertEquals(cl2.getUuid(), carLink.getDestination());

		assertEquals(1, cp.getTrafficLights().size());
		SensorTrafficLight trafficLight = (SensorTrafficLight) cp.getTrafficLights().get(cl1.getUuid());
		assertEquals(5.0d, trafficLight.getAnticipation(), 0.0d);
		assertEquals(1, trafficLight.getActivators().size());
		assertEquals(tl1.getUuid(), trafficLight.getActivators().iterator().next());

	}

}
