package tramways.model.analysis.availability;

import org.oristool.petrinet.PetriNet;

import tramways.dto.RoadMap;
import tramways.dto.lanes.LaneSegmentDto;
import tramways.dto.points.SourcePointDto;
import tramways.dto.points.trafficlight.TrafficLightCrossingPointDto;

public class CrossingPointPetriNetMapper {

	private String carLane;

	public PetriNet map(RoadMap map) {
		TrafficLightCrossingPointDto tl = map.getPoints(TrafficLightCrossingPointDto.class).iterator().next();
		LaneSegmentDto target = map.getLane(carLane);
		
		SourcePointDto sourcePoint = map.getPoint(target.getSource(), SourcePointDto.class);
		target.getDestination().equals(tl.getUuid());
		return null;
	}

}
