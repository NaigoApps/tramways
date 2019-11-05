package tramways.model;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import tramways.model.roadmap.RoadMap;
import tramways.model.roadmap.lanes.LaneSegment;
import tramways.model.roadmap.points.CrossingPoint;
import tramways.model.roadmap.points.DestinationPoint;
import tramways.model.roadmap.points.LaneSegmentLink;
import tramways.model.roadmap.points.SourcePoint;
import tramways.model.roadmap.points.SourcePointType;

public class CrossroadTest {

	@Test
	public void testBuild() {
		LaneSegment cA = new LaneSegment();
		LaneSegment cB = new LaneSegment();

		LaneSegment tA = new LaneSegment();
		LaneSegment tB = new LaneSegment();
		
		SourcePoint csp = new SourcePoint();
		csp.setKind(SourcePointType.CAR);
		csp.setTargetLane(cA.getUuid());
		
		SourcePoint tsp = new SourcePoint();
		tsp.setKind(SourcePointType.TRAM);
		tsp.setTargetLane(tA.getUuid());
		
		DestinationPoint cdp = new DestinationPoint();
		cdp.getLanes().add(cB.getUuid());
		
		DestinationPoint tdp = new DestinationPoint();
		tdp.getLanes().add(tB.getUuid());
		
		CrossingPoint crossing = new CrossingPoint();
		
		Map<String, Set<LaneSegmentLink>> constraints = new HashMap<>();
		constraints.put(tA.getUuid(), new HashSet<>());
		constraints.get(tA.getUuid()).add(new LaneSegmentLink(tB.getUuid()));
		constraints.put(cA.getUuid(), new HashSet<>());
		constraints.get(cA.getUuid()).add(new LaneSegmentLink(cB.getUuid()));
		crossing.setConstraints(constraints);
		
		RoadMap map = new RoadMap();
		map.getPoints().add(csp);
		map.getPoints().add(tsp);
		map.getPoints().add(cdp);
		map.getPoints().add(tdp);
		map.getPoints().add(crossing);
		map.initialize();
		
		assertEquals(2, crossing.getConstraints().size());
		assertEquals(tB.getUuid(), crossing.getConstraints(tA.getUuid()).iterator().next().getDestination());
		assertEquals(cB.getUuid(), crossing.getConstraints(cA.getUuid()).iterator().next().getDestination());

		assertEquals(crossing.getUuid(), map.getLane(tA.getUuid()).getDestination());
		assertEquals(crossing.getUuid(), map.getLane(cA.getUuid()).getDestination());
		assertEquals(crossing.getUuid(), map.getLane(tB.getUuid()).getSource());
		assertEquals(crossing.getUuid(), map.getLane(cB.getUuid()).getSource());
		
	}

}
