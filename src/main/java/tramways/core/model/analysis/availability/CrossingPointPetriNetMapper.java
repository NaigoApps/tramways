package tramways.core.model.analysis.availability;

import org.oristool.petrinet.PetriNet;

import tramways.core.model.roadmap.RoadMap;
import tramways.core.model.roadmap.lanes.LaneSegment;
import tramways.core.model.roadmap.points.SourcePoint;
import tramways.core.model.roadmap.points.trafficlight.TrafficLightCrossingPoint;

public class CrossingPointPetriNetMapper {

	private String carLane;

	public PetriNet map(RoadMap map) {
		TrafficLightCrossingPoint tl = map.getPoints(TrafficLightCrossingPoint.class).iterator().next();
		LaneSegment target = map.getLane(carLane);

		SourcePoint sourcePoint = map.getPoint(target.getSource().getUuid(), SourcePoint.class);
		target.getDestination().equals(tl.getUuid());
		return null;
	}

}
