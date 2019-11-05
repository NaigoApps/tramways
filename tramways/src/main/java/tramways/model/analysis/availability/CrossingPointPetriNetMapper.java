package tramways.model.analysis.availability;

import org.oristool.petrinet.PetriNet;

import tramways.model.roadmap.RoadMap;
import tramways.model.roadmap.lanes.LaneSegment;
import tramways.model.roadmap.points.SourcePoint;
import tramways.model.roadmap.points.trafficlight.TrafficLightCrossingPoint;

public class CrossingPointPetriNetMapper {

	private String carLane;

	public PetriNet map(RoadMap map) {
		TrafficLightCrossingPoint tl = map.getPoints(TrafficLightCrossingPoint.class).iterator().next();
		LaneSegment target = map.getLane(carLane);
		
		SourcePoint sourcePoint = map.getPoint(target.getSource(), SourcePoint.class);
		target.getDestination().equals(tl.getUuid());
		return null;
	}

}
