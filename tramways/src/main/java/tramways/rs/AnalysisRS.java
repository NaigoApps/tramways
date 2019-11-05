package tramways.rs;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import tramways.dto.AnalysisTypeDto;
import tramways.dto.mappers.AnalysisTypeMapper;
import tramways.dto.mappers.PropertyMetadataMapper;
import tramways.inbound.AnalysisService;
import tramways.inbound.ProjectService;
import tramways.model.analysis.AnalysisType;
import tramways.model.properties.PropertyMetadataDto;
import tramways.model.roadmap.RoadMap;

@Path("analysis")
@Transactional
public class AnalysisRS {

	@Inject
	private PropertyMetadataMapper propertyMetadataMapper;

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
	public List<PropertyMetadataDto> getParameters(@PathParam("typeId") String analysisTypeId,
			@PathParam("project") String project, @PathParam("map") String map) {
		AnalysisType<?> type = service.getAnalysisType(analysisTypeId);
		RoadMap roadMap = projectService.retrieveProject(project).getMap(map).getContent();
		return service.getRequiredParameters(type, roadMap).stream()
				.map(propertyMetadataMapper::map)
				.collect(Collectors.toList());
	}
}
