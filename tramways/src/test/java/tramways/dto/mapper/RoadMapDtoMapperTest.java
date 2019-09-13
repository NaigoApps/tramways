package tramways.dto.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import tramways.dto.RoadMapDto;
import tramways.dto.distributions.ConstantDistributionDto;
import tramways.dto.distributions.ExponentialDistributionDto;
import tramways.dto.distributions.UniformDistributionDto;
import tramways.dto.lanes.CarLaneSegmentDto;
import tramways.dto.lanes.TramLaneSegmentDto;
import tramways.dto.points.CarSourcePointDto;
import tramways.dto.points.DestinationPointDto;
import tramways.dto.points.LaneSegmentLinkDto;
import tramways.dto.points.TramSourcePointDto;
import tramways.dto.points.trafficlight.SensorTrafficLightDto;
import tramways.dto.points.trafficlight.TrafficLightCrossingPointDto;

public class RoadMapDtoMapperTest {

	private RoadMapDtoMapper mapper;

	@Before
	public void setUp() {
		mapper = new RoadMapDtoMapper();
	}

	@Test
	public void testMap01() {
		RoadMapDto dto = mapper.map(readJson("json/roads_01.json"));
		assertTrue(dto.getLanes().isEmpty());
		assertTrue(dto.getPoints().isEmpty());
	}

	@Test
	public void testMap02() {
		RoadMapDto dto = mapper.map(readJson("json/roads_02.json"));
		assertEquals(1, dto.getLanes().size());
		assertEquals(2, dto.getPoints().size());
		CarSourcePointDto s = (CarSourcePointDto) dto.getPoints().get(0);
		CarLaneSegmentDto l = (CarLaneSegmentDto) dto.getLanes().get(0);
		DestinationPointDto d = (DestinationPointDto) dto.getPoints().get(1);

		assertTrue(l.getSource().equals(s.getUuid()));
		assertTrue(l.getDestination().equals(d.getUuid()));
		assertEquals(1.0d, l.getLength().doubleValue(), 0.0d);
		assertEquals(2.0d, l.getVehicleMaxSpeed().doubleValue(), 0.0d);
		assertEquals(3.0d, l.getVehicleMinGap().doubleValue(), 0.0d);
		assertEquals(4.0d, l.getVehicleLength().doubleValue(), 0.0d);

		assertEquals(l.getUuid(), s.getTargetLane());
		ExponentialDistributionDto arrivalRate = (ExponentialDistributionDto) s.getArrivalRate();
		assertEquals(1.0d, arrivalRate.getMean(), 0.0d);

		assertEquals(1, d.getLanes().size());
		assertEquals(l.getUuid(), d.getLanes().get(0));
	}

	@Test
	public void testMap03() {
		RoadMapDto dto = mapper.map(readJson("json/roads_03.json"));
		assertEquals(1, dto.getLanes().size());
		assertEquals(2, dto.getPoints().size());
		TramSourcePointDto s = (TramSourcePointDto) dto.getPoints().get(0);
		TramLaneSegmentDto l = (TramLaneSegmentDto) dto.getLanes().get(0);
		DestinationPointDto d = (DestinationPointDto) dto.getPoints().get(1);

		assertTrue(l.getSource().equals(s.getUuid()));
		assertTrue(l.getDestination().equals(d.getUuid()));
		assertEquals(1.0d, l.getLength().doubleValue(), 0.0d);
		assertEquals(2.0d, l.getVehicleMaxSpeed().doubleValue(), 0.0d);
		assertEquals(3.0d, l.getVehicleMinGap().doubleValue(), 0.0d);
		assertEquals(4.0d, l.getVehicleLength().doubleValue(), 0.0d);

		assertEquals(5, s.getPeriod().intValue());
		assertEquals(l.getUuid(), s.getTargetLane());

		assertEquals(1, d.getLanes().size());
		assertEquals(l.getUuid(), d.getLanes().get(0));
	}

	@Test
	public void testMap04() {
		RoadMapDto dto = mapper.map(readJson("json/roads_04.json"));
		assertEquals(4, dto.getLanes().size());
		assertEquals(5, dto.getPoints().size());

		CarSourcePointDto cs = (CarSourcePointDto) dto.getPoint("c_S");
		DestinationPointDto cd = (DestinationPointDto) dto.getPoint("c_D");
		TramSourcePointDto ts = (TramSourcePointDto) dto.getPoint("t_S");
		DestinationPointDto td = (DestinationPointDto) dto.getPoint("t_D");
		TrafficLightCrossingPointDto cp = (TrafficLightCrossingPointDto) dto.getPoint("cp");

		CarLaneSegmentDto cl1 = (CarLaneSegmentDto) dto.getLane("cl_A");
		CarLaneSegmentDto cl2 = (CarLaneSegmentDto) dto.getLane("cl_B");
		TramLaneSegmentDto tl1 = (TramLaneSegmentDto) dto.getLane("tl_A");
		TramLaneSegmentDto tl2 = (TramLaneSegmentDto) dto.getLane("tl_B");

		assertEquals(cl1.getUuid(), cs.getTargetLane());
		assertEquals(tl1.getUuid(), ts.getTargetLane());

		assertEquals(cp.getUuid(), cl1.getDestination());
		assertEquals(cp.getUuid(), tl1.getDestination());
		assertEquals(cp.getUuid(), cl2.getSource());
		assertEquals(cp.getUuid(), tl2.getSource());

		assertEquals(cd.getUuid(), cl2.getDestination());
		assertEquals(td.getUuid(), tl2.getDestination());

		assertEquals(1, cp.getConstraints().get(tl1.getUuid()).size());
		LaneSegmentLinkDto tramLink = cp.getConstraints().get(tl1.getUuid()).iterator().next();
		assertEquals(tl2.getUuid(), tramLink.getDestination());
		assertEquals(1.0d, tramLink.getWeight(), 0.0d);
		assertEquals(5.0d, ((ConstantDistributionDto) tramLink.getCrossingTime()).getValue(), 0.0d);
		assertEquals(tl2.getUuid(), tramLink.getDestination());

		assertEquals(1, cp.getConstraints().get(cl1.getUuid()).size());
		LaneSegmentLinkDto carLink = cp.getConstraints().get(cl1.getUuid()).iterator().next();
		assertEquals(1.0d, carLink.getWeight(), 0.0d);
		assertEquals(4.0d, ((UniformDistributionDto) carLink.getCrossingTime()).getMin(), 0.0d);
		assertEquals(6.0d, ((UniformDistributionDto) carLink.getCrossingTime()).getMax(), 0.0d);
		assertEquals(cl2.getUuid(), carLink.getDestination());
		
		assertEquals(1, cp.getTrafficLights().size());
		SensorTrafficLightDto trafficLight = (SensorTrafficLightDto) cp.getTrafficLights().get(cl1.getUuid());
		assertEquals(5.0d, trafficLight.getAnticipation(), 0.0d);
		assertEquals(1, trafficLight.getActivators().size());
		assertEquals(tl1.getUuid(), trafficLight.getActivators().iterator().next());

	}

	private String readJson(String file) {
		try {
			byte[] raw = Files.readAllBytes(Paths.get("src/test/resources/" + file));
			return new String(raw, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
