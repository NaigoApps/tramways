package tramways.core.model.analysis;

import tramways.core.model.roadmap.NetworkMap;
import tramways.inbound.model.Property;
import tramways.inbound.model.RoadMapContent;
import tramways.services.MessagesCollector;

import java.util.List;

public interface AnalysisType {
	String getId();
	String getName();
	Analysis createAnalysis(NetworkMap networkMap, List<Property> parameters);
	void prepareAnalysis(RoadMapContent map, List<Property> parameters, PropertiesCollector propertiesCollector, MessagesCollector messagesCollector);
	List<Property> prepareAnalysis(String category);
}
