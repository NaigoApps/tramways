package tramways.rs;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import tramways.inbound.ProjectService;
import tramways.mapper.ProjectMapper;
import tramways.model.analysis.AnalysisType;
import tramways.model.analysis.AnalysisTypeFactory;
import tramways.model.properties.PropertyMetadataDto;
import tramways.services.RequestSession;

@Path("analysis")
@Transactional
public class AnalysisRS {

	@Inject
	private ProjectService service;

	@Inject
	private ProjectMapper mapper;

	@Inject
	private RequestSession session;

	@Inject
	private AnalysisTypeFactory analysisTypeFactory;

	@GET
	@Path("types")
	public List<String> availableAnalysis() {
		return analysisTypeFactory.getTypes().stream().map(AnalysisType::getName).collect(Collectors.toList());
	}

	@GET
	@Path("{name}/params/{project}/{map}")
	public List<PropertyMetadataDto> getParameters(@PathParam("name") String analysisType,
			@PathParam("project") String project, @PathParam("map") String map) {
		return analysisTypeFactory.getType(analysisType).getRequiredParameters();
	}
}
