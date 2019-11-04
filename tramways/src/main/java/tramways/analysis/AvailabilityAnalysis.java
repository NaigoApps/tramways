package tramways.analysis;

import org.oristool.petrinet.PetriNet;

import tramways.dto.RoadMap;

public class AvailabilityAnalysis {
	private String crossingPointUuid;
	private String trafficLightUuid;

	public String getCrossingPoint() {
		return crossingPointUuid;
	}

	public void setCrossingPoint(String crossingPoint) {
		this.crossingPointUuid = crossingPoint;
	}

	public void setTrafficLight(String trafficLight) {
		this.trafficLightUuid = trafficLight;
	}

	public String getTrafficLight() {
		return trafficLightUuid;
	}
	
	public void analyze(RoadMap map) {
		CrossingPointPetriNetMapper mapper = new CrossingPointPetriNetMapper();
		mapper.setCrossingPoint(crossingPointUuid);
		mapper.setTrafficLight(trafficLightUuid);
		PetriNet net = mapper.map(map);
	}
}
