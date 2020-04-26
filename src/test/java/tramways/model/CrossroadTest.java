package tramways.model;

import org.junit.Test;
import tramways.core.model.roadmap.NetworkMap;
import tramways.core.model.roadmap.lanes.LaneSegment;
import tramways.core.model.roadmap.points.NetworkPoint;

import static org.junit.Assert.assertEquals;

public class CrossroadTest {

	@Test
	public void testBuild() {
		LaneSegment cA = new LaneSegment();
		LaneSegment cB = new LaneSegment();

		LaneSegment tA = new LaneSegment();
		LaneSegment tB = new LaneSegment();

		NetworkPoint csp = new NetworkPoint();
		NetworkPoint tsp = new NetworkPoint();
		NetworkPoint cp = new NetworkPoint();
		NetworkPoint cdp = new NetworkPoint();
		NetworkPoint tdp = new NetworkPoint();

		csp.startLane(cA);
		cp.addLink(cA, cB);
		cdp.endLane(cB);

		tsp.startLane(tA);
		cp.addLink(tA, tB);
		tdp.endLane(tB);

		NetworkMap map = new NetworkMap();
		map.addPoints(csp, tsp, cdp, tdp, cp);

		assertEquals(2, cp.getLinks().size());
		assertEquals(tA, cp.getLinks(tA).iterator().next().getSource());
		assertEquals(tB, cp.getLinks(tA).iterator().next().getDestination());
		assertEquals(cA, cp.getLinks(cA).iterator().next().getSource());
		assertEquals(cB, cp.getLinks(cA).iterator().next().getDestination());
	}

}
