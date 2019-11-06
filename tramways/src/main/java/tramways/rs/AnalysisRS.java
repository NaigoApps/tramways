package tramways.rs;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import tramways.dto.AnalysisTypeDto;
import tramways.dto.mappers.AnalysisTypeMapper;
import tramways.inbound.AnalysisService;
import tramways.inbound.ProjectService;
import tramways.model.analysis.AnalysisType;
import tramways.model.properties.Property;
import tramways.model.roadmap.RoadMap;

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
		return service.getAvailableAnalysis().stream()
				.map(mapper::map)
				.collect(Collectors.toList());
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
	public String launchAnalysis(@PathParam("typeId") String analysisTypeId,
			@PathParam("project") String project, @PathParam("map") String map, List<Property> parameters) {
		AnalysisType type = service.getAnalysisType(analysisTypeId);
		RoadMap roadMap = projectService.retrieveProject(project).getMap(map).getContent();
		return service.launchAnalysis(type, roadMap, parameters);
	}
}
