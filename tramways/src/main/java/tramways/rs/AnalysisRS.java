package tramways.rs;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import tramways.core.model.analysis.AnalysisType;
import tramways.core.model.persistable.projects.Project;
import tramways.core.model.propertiess.Property;
import tramways.core.model.roadmap.RoadMap;
import tramways.dto.AnalysisTypeDto;
import tramways.dto.mappers.AnalysisTypeMapper;
import tramways.inbound.AnalysisService;
import tramways.inbound.ProjectService;

@Path("analysis")
@Transactional
public class AnalysisRS {

	@Inject
	private AnalysisTypeMapper mapper;

	@Inject
	private AnalysisService service;

	@Inject
	private ProjectService projectService;

	@GET
	@Path("types")
	public List<AnalysisTypeDto> availableAnalysis() {
		return service.getAvailableAnalysis().stream().map(mapper::map).collect(Collectors.toList());
	}

	@GET
	@Path("{typeId}/params/{project}/{map}")
	public List<Property> getParameters(@PathParam("typeId") String analysisTypeId,
			@PathParam("project") String project, @PathParam("map") String map) {
		AnalysisType type = service.getAnalysisType(analysisTypeId);
		RoadMap roadMap = projectService.retrieveProject(project).getMap(map).getContent();
		return service.getRequiredParameters(type, roadMap);
	}

	@POST
	@Path("{typeId}/launch/{project}/{map}")
	public void launchAnalysis(@PathParam("typeId") String analysisTypeId, @PathParam("project") String project,
			@PathParam("map") String map, @QueryParam("name") String name, List<Property> parameters) {
		AnalysisType type = service.getAnalysisType(analysisTypeId);
		service.launchAnalysis(type, name, project, map, parameters);
	}
}
