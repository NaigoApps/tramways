package tramways.inbound.impl;

import java.util.List;

import javax.inject.Inject;

import tramways.inbound.AnalysisService;
import tramways.model.analysis.Analysis;
import tramways.model.analysis.AnalysisType;
import tramways.model.analysis.AnalysisTypeFactory;
import tramways.model.analysis.result.AnalysisResult;
import tramways.model.persistable.projects.AnalysisWrapper;
import tramways.model.persistable.projects.Project;
import tramways.model.persistable.projects.RoadMapWrapper;
import tramways.model.properties.Property;
import tramways.model.roadmap.RoadMap;
import tramways.outbound.ProjectRepository;

public class AnalysisServiceImpl implements AnalysisService {

	@Inject
	private AnalysisTypeFactory analysisTypeFactory;

	@Inject
	private ProjectRepository projectsRepository;

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
	public void launchAnalysis(AnalysisType type, String name, String projectId, String mapId,
			List<Property> parameters) {
		Project project = projectsRepository.findByUuid(projectId);
		RoadMapWrapper map = project.getMap(mapId);

		Analysis analysis = type.createAnalysis(parameters);
		AnalysisResult result = analysis.run();

		AnalysisWrapper persistable = new AnalysisWrapper();
		persistable.setName(name);
		persistable.assignResult(result);
		map.addAnalysis(persistable);
	}

}
