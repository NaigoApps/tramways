package tramways.inbound;

import java.util.List;

import tramways.model.analysis.AnalysisType;
import tramways.model.properties.Property;
import tramways.model.roadmap.RoadMap;

public interface AnalysisService {

	public List<AnalysisType> getAvailableAnalysis();
	public List<Property> getRequiredParameters(AnalysisType type, RoadMap map);
	public AnalysisType getAnalysisType(String analysisType);
	public void launchAnalysis(AnalysisType type, String name, String projectId, String mapId, List<Property> parameters);

}
