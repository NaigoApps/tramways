package tramways.core.model.analysis.availability;

import java.util.Arrays;
import java.util.List;

import tramways.core.model.analysis.Analysis;
import tramways.core.model.analysis.AnalysisType;
import tramways.core.model.propertiess.Property;
import tramways.core.model.propertiess.StringProperty;
import tramways.core.model.roadmap.RoadMap;
import tramways.core.model.roadmap.points.CrossingPoint;
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
