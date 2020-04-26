package tramways.core.model.analysis.availability;

import org.oristool.petrinet.PetriNet;

import tramways.core.model.roadmap.NetworkMap;
import tramways.core.model.roadmap.lanes.LaneSegment;
import tramways.core.model.roadmap.points.NetworkPoint;

public class CrossingPointPetriNetMapper {

	private String carLane;

	public PetriNet map(NetworkMap map) {
		NetworkPoint tl = map.listPoints().iterator().next();
//		LaneSegment target = map.getLane(carLane);

//		SourcePoint sourcePoint = map.getPoint(target.getSource().getUuid(), SourcePoint.class);
//		target.getDestination().equals(tl.getUuid());
		return null;
	}

}
