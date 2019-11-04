package tramways.model.analysis;

import java.util.List;

import tramways.dto.RoadMap;
import tramways.model.properties.PropertyMetadataDto;
import tramways.services.MessageCollector;

public interface AnalysisType<O extends AnalysisOptions> {
	public abstract String getName();
	public abstract Analysis createAnalysis();
	public abstract boolean isApplicable(RoadMap map, O options, MessageCollector collector);
	public abstract List<PropertyMetadataDto> getRequiredParameters();
}
