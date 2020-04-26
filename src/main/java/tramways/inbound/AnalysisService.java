package tramways.inbound;

import java.util.List;

import tramways.core.model.analysis.AnalysisType;
import tramways.core.model.roadmap.NetworkMap;
import tramways.inbound.model.Property;

public interface AnalysisService {

	List<AnalysisType> getAvailableAnalysis();
	List<Property> getRequiredParameters(AnalysisType type, NetworkMap map);
	AnalysisType getAnalysisType(String analysisType);
	void launchAnalysis(AnalysisType type, String name, String projectId, String mapId, List<Property> parameters);

}
