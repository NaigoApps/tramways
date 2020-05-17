package tramways.inbound.impl;

import tramways.core.model.analysis.AnalysisTypeFactory;
import tramways.core.model.persistable.projects.Project;
import tramways.core.model.persistable.projects.RoadMap;
import tramways.dto.mappers.AnalysisTypeMapper;
import tramways.inbound.RestUtils;
import tramways.inbound.api.AnalysisApiService;
import tramways.inbound.api.NotFoundException;
import tramways.inbound.model.AnalysisLaunchRequest;
import tramways.inbound.model.AnalysisParamsRequest;
import tramways.inbound.model.RoadMapContent;
import tramways.outbound.ProjectRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.Collections;
import java.util.stream.Collectors;

@Transactional
public class AnalysisApiServiceImpl implements AnalysisApiService {

    @Inject
    private AnalysisTypeFactory factory;

    @Inject
    private AnalysisTypeMapper mapper;

    @Inject
    private ProjectRepository projectRepository;

    @Override
    public Response getAnalysisParams(AnalysisParamsRequest request, SecurityContext securityContext) throws NotFoundException {
        Project project = projectRepository.findById(request.getProjectId());
        RoadMap map = project.getMap(request.getMapId());
        return RestUtils.ok(factory.getType(request.getAnalysisTypeId()).getParameters(map.getContent()));
    }

    @Override
    public Response getAvailableAnalysis(String projectId, String mapId, SecurityContext securityContext) throws NotFoundException {
        Project project = projectRepository.findById(projectId);
        RoadMap map = project.getMap(mapId);
        RoadMapContent mapContent = map.getContent();
        return RestUtils.ok(factory.getTypes().stream()
                .map(type -> mapper.map(type, mapContent, Collections.emptyList()))
                .collect(Collectors.toList()));
    }

    @Override
    public Response launchAnalysis(AnalysisLaunchRequest analysisLaunchRequest, SecurityContext securityContext) throws NotFoundException {
        return null;
    }

    /*
    LAUNCH
    Project project = projectsRepository.findByUuid(projectId);
		RoadMap map = project.getMap(mapId);

		tramways.core.model.analysis.Analysis analysis = type.createAnalysis(parameters);
		AnalysisResult result = analysis.run();

		Analysis persistable = new Analysis();
		persistable.setName(name);
		persistable.assignResult(result);
		map.addAnalysis(persistable);
     */

}
