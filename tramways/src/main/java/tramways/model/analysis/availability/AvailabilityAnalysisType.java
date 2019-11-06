package tramways.model.analysis.availability;

import java.util.Arrays;
import java.util.List;

import tramways.model.analysis.Analysis;
import tramways.model.analysis.AnalysisType;
import tramways.model.properties.Property;
import tramways.model.properties.StringProperty;
import tramways.model.roadmap.RoadMap;
import tramways.model.roadmap.points.CrossingPoint;
import tramways.services.MessageCollector;

public class AvailabilityAnalysisType implements AnalysisType{

	@Override
	public String getId() {
		return "availability";
	}
	
	@Override
	public String getName() {
		return "Availability";
	}

	@Override
	public Analysis createAnalysis(List<Property> params) {
		return new AvailabilityAnalysis();
	}

	@Override
	public boolean isApplicable(RoadMap map, List<Property> options, MessageCollector collector) {
		List<CrossingPoint> crossingPoints = map.getPoints(CrossingPoint.class);
		if(crossingPoints.isEmpty()) {
			return collector.addMessage("No crossing points found");
		}
		if(crossingPoints.size() > 1) {
			return collector.addMessage("Multiple crossing points found");
		}
		return true;
	}
	
	@Override
	public List<Property> getRequiredParameters(RoadMap map) {
		StringProperty prop = new StringProperty("Car lane");
		return Arrays.asList(prop);
	}

}
