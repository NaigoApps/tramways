package tramways.model.analysis;

import java.util.List;

import tramways.model.properties.Property;
import tramways.model.roadmap.RoadMap;
import tramways.services.MessageCollector;

public interface AnalysisType {
	public abstract String getId();
	public abstract String getName();
	public abstract Analysis createAnalysis(List<Property> parameters);
	public abstract boolean isApplicable(RoadMap map, List<Property> parameters, MessageCollector collector);
	public abstract List<Property> getRequiredParameters(RoadMap map);
}
