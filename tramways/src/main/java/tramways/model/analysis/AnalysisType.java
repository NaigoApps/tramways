package tramways.model.analysis;

import java.util.List;

import tramways.model.persistable.properties.PropertyMetadata;
import tramways.model.roadmap.RoadMap;
import tramways.services.MessageCollector;

public interface AnalysisType<O extends AnalysisOptions> {
	public abstract String getId();
	public abstract String getName();
	public abstract Analysis createAnalysis();
	public abstract boolean isApplicable(RoadMap map, O options, MessageCollector collector);
	public abstract List<PropertyMetadata> getRequiredParameters(RoadMap map);
}
