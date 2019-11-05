package tramways.inbound;

import java.util.List;

import tramways.model.analysis.AnalysisType;
import tramways.model.persistable.properties.PropertyMetadata;
import tramways.model.roadmap.RoadMap;

public interface AnalysisService {

	public List<AnalysisType<?>> getAvailableAnalysis();
	public List<PropertyMetadata> getRequiredParameters(AnalysisType<?> type, RoadMap map);
	public AnalysisType<?> getAnalysisType(String analysisType);

}
