package tramways.inbound.impl;

import java.util.List;

import javax.inject.Inject;

import tramways.inbound.AnalysisService;
import tramways.model.analysis.AnalysisType;
import tramways.model.analysis.AnalysisTypeFactory;
import tramways.model.persistable.properties.PropertyMetadata;
import tramways.model.roadmap.RoadMap;

public class AnalysisServiceImpl implements AnalysisService {

	@Inject
	private AnalysisTypeFactory analysisTypeFactory;
	
	@Override
	public List<AnalysisType<?>> getAvailableAnalysis() {
		return analysisTypeFactory.getTypes();
	}

	@Override
	public List<PropertyMetadata> getRequiredParameters(AnalysisType<?> type, RoadMap map) {
		return type.getRequiredParameters(map);
	}
	
	@Override
	public AnalysisType<?> getAnalysisType(String analysisType) {
		for(AnalysisType<?> type : analysisTypeFactory.getTypes()) {
			if(type.getId().equals(analysisType)) {
				return type;
			}
		}
		return null;
	}

}
