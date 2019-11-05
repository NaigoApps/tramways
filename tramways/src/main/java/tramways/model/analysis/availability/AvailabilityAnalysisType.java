package tramways.model.analysis.availability;

import java.util.Arrays;
import java.util.List;

import tramways.model.analysis.Analysis;
import tramways.model.analysis.AnalysisType;
import tramways.model.persistable.properties.PropertyMetadata;
import tramways.model.properties.PropertyType;
import tramways.model.roadmap.RoadMap;
import tramways.model.roadmap.points.CrossingPoint;
import tramways.services.MessageCollector;

public class AvailabilityAnalysisType implements AnalysisType<AvailabilityAnalysisOptions>{

	@Override
	public String getId() {
		return "availability";
	}
	
	@Override
	public String getName() {
		return "Availability";
	}

	@Override
	public Analysis createAnalysis() {
		return null;
	}

	@Override
	public boolean isApplicable(RoadMap map, AvailabilityAnalysisOptions options, MessageCollector collector) {
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
	public List<PropertyMetadata> getRequiredParameters(RoadMap map) {
		PropertyMetadata metadata = new PropertyMetadata("Car lane", PropertyType.STRING);
		return Arrays.asList(metadata);
	}

}
