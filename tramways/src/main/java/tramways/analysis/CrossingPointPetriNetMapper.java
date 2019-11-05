package tramways.analysis;

import org.oristool.petrinet.PetriNet;

import tramways.model.roadmap.RoadMap;
import tramways.model.roadmap.lanes.LaneSegment;
import tramways.model.roadmap.points.SourcePoint;
import tramways.model.roadmap.points.trafficlight.TrafficLightCrossingPoint;

public class CrossingPointPetriNetMapper {

	private String crossingPoint;
	private String trafficLight;
	private String carLine;

	public void setCrossingPoint(String crossingPointUuid) {
		this.crossingPoint = crossingPointUuid;
	}

	public void setTrafficLight(String trafficLightUuid) {
		this.trafficLight = trafficLightUuid;
	}
	
	public PetriNet map(RoadMap map) {
		TrafficLightCrossingPoint tl = map.getPoint(crossingPoint, TrafficLightCrossingPoint.class);
		LaneSegment target = map.getLane(carLine);
		
		SourcePoint sourcePoint = map.getPoint(target.getSource(), SourcePoint.class);
		target.getDestination().equals(crossingPoint);
		return null;
	}

}
