package tramways.analysis;

import org.oristool.petrinet.PetriNet;

import tramways.dto.RoadMapDto;
import tramways.dto.lanes.LaneSegmentDto;
import tramways.dto.points.SourcePointDto;
import tramways.dto.points.trafficlight.TrafficLightCrossingPointDto;

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
	
	public PetriNet map(RoadMapDto map) {
		TrafficLightCrossingPointDto tl = map.getPoint(crossingPoint, TrafficLightCrossingPointDto.class);
		LaneSegmentDto target = map.getLane(carLine);
		
		SourcePointDto sourcePoint = map.getPoint(target.getSource(), SourcePointDto.class);
		target.getDestination().equals(crossingPoint);
		return null;
	}

}
