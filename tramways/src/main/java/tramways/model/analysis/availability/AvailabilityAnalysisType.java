package tramways.model.analysis.availability;

import java.util.ArrayList;
import java.util.List;

import tramways.dto.RoadMap;
import tramways.dto.points.CrossingPointDto;
import tramways.model.analysis.Analysis;
import tramways.model.analysis.AnalysisType;
import tramways.model.properties.PropertyMetadataDto;
import tramways.model.properties.PropertyType;
import tramways.services.MessageCollector;

public class AvailabilityAnalysisType implements AnalysisType<AvailabilityAnalysisOptions>{

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
		List<CrossingPointDto> crossingPoints = map.getPoints(CrossingPointDto.class);
		if(crossingPoints.isEmpty()) {
			return collector.addMessage("No crossing points found");
		}
		if(crossingPoints.size() > 1) {
			return collector.addMessage("Multiple crossing points found");
		}
		return true;
	}
	
	@Override
	public List<PropertyMetadataDto> getRequiredParameters() {
		List<PropertyMetadataDto> params = new ArrayList<>();
		params.add(new PropertyMetadataDto("Car lane", PropertyType.STRING.name()));
		return params;
	}

}
