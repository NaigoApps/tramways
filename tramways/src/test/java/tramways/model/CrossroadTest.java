package tramways.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tramways.model.roadmap.lanes.LaneSegment;
import tramways.model.roadmap.points.CrossingPoint;

public class CrossroadTest {

	@Test
	public void testBuild() {
		
		LaneSegment tA = new LaneSegment();
		LaneSegment tB = new LaneSegment();
		
		LaneSegment cA = new LaneSegment();
		LaneSegment cB = new LaneSegment();
		
		CrossingPoint crossing = new CrossingPoint();
		
		crossing.addLink(tA, tB);
		crossing.addLink(cA, cB);
		
		assertEquals(2, crossing.getSources().size());
		assertEquals(2, crossing.getDestinations().size());

		assertEquals(crossing, tA.getDestination());
		assertEquals(crossing, cA.getDestination());
		assertEquals(crossing, tB.getSource());
		assertEquals(crossing, cB.getSource());
	}

}
