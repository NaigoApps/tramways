package tramways.inbound.impl;

import java.util.List;

import javax.inject.Inject;

import tramways.core.model.analysis.AnalysisType;
import tramways.core.model.analysis.AnalysisTypeFactory;
import tramways.core.model.analysis.result.AnalysisResult;
import tramways.core.model.persistable.projects.Analysis;
import tramways.core.model.persistable.projects.Project;
import tramways.core.model.persistable.projects.RoadMap;
import tramways.core.model.properties.Property;
import tramways.inbound.AnalysisService;
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
	public List<Property> getRequiredParameters(AnalysisType type, tramways.core.model.roadmap.RoadMap map) {
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
		RoadMap map = project.getMap(mapId);

		tramways.core.model.analysis.Analysis analysis = type.createAnalysis(parameters);
		AnalysisResult result = analysis.run();

		Analysis persistable = new Analysis();
		persistable.setName(name);
		persistable.assignResult(result);
		map.addAnalysis(persistable);
	}

}
