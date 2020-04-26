package tramways.core.model.analysis;

import tramways.inbound.model.Property;
import tramways.inbound.model.RoadMapContent;
import tramways.services.MessageCollector;

import java.util.List;

public interface AnalysisType {
	String getId();
	String getName();
	Analysis createAnalysis(List<Property> parameters);
	boolean isApplicable(RoadMapContent map, List<Property> parameters, MessageCollector collector);
	List<Property> getParameters(RoadMapContent map);
}
