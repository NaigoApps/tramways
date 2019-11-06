package tramways.inbound.impl;

import java.util.List;

import javax.inject.Inject;

import tramways.inbound.AnalysisService;
import tramways.model.analysis.Analysis;
import tramways.model.analysis.AnalysisType;
import tramways.model.analysis.AnalysisTypeFactory;
import tramways.model.properties.Property;
import tramways.model.roadmap.RoadMap;

public class AnalysisServiceImpl implements AnalysisService {

	@Inject
	private AnalysisTypeFactory analysisTypeFactory;
	
	@Override
	public List<AnalysisType> getAvailableAnalysis() {
		return analysisTypeFactory.getTypes();
	}

	@Override
	public List<Property> getRequiredParameters(AnalysisType type, RoadMap map) {
		return type.getRequiredParameters(map);
	}
	
	@Override
	public AnalysisType getAnalysisType(String analysisType) {
		return analysisTypeFactory.getType(analysisType);
	}
	
	@Override
	public String launchAnalysis(AnalysisType type, RoadMap roadMap, List<Property> parameters) {
		Analysis analysis = type.createAnalysis(parameters);
		return analysis.run();
	}

}
