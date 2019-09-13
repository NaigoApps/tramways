package tramways.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tramways.model.points.CrossingPoint;
import tramways.model.streets.CarLaneSegment;
import tramways.model.streets.TramLaneSegment;

public class CrossroadTest {

	@Test
	public void testBuild() {
		
		TramLaneSegment tA = new TramLaneSegment();
		TramLaneSegment tB = new TramLaneSegment();
		
		CarLaneSegment cA = new CarLaneSegment();
		CarLaneSegment cB = new CarLaneSegment();
		
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
