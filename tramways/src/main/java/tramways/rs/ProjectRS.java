package tramways.rs;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import tramways.core.model.persistable.projects.Project;
import tramways.dto.ProjectDto;
import tramways.dto.Wrapper;
import tramways.dto.mappers.ProjectMapper;
import tramways.inbound.ProjectService;
import tramways.services.RequestSession;

@Path("projects")
@Transactional
public class ProjectRS {

	@Inject
	private ProjectService service;
	
	@Inject
	private ProjectMapper mapper;
	
	@Inject
	private RequestSession session;
	
	@GET
	public List<ProjectDto> listProjects(){
		return service.retrieveProjects(session.getLoggedUserUuid()).stream()
				.map(mapper::map)
				.collect(Collectors.toList());
	}
	
	@GET
	@Path("{projectUuid}")
	public ProjectDto getProject(@PathParam("projectUuid") String projectUuid){
		return mapper.map(service.retrieveProject(projectUuid));
	}
	
	@POST
	public String createProject(ProjectDto dto) {
		Project project = mapper.map(dto);
		service.createProject(project);
		return project.getUuid();
	}
	
	@PUT
	@Path("{projectUuid}")
	public void updateProject(@PathParam("projectUuid") String uuid, ProjectDto dto) {
		service.updateProject(uuid, dto.getName());
	}
	
	@POST
	@Path("{projectUuid}/maps/{mapUuid}/clone")
	public void duplicateMap(@PathParam("projectUuid") String projectUuid, @PathParam("mapUuid") String mapUuid, Wrapper<String> name) {
		service.duplicateMap(projectUuid, mapUuid, name.getValue());
	}
	
	@PUT
	@Path("{projectUuid}/maps/{mapUuid}")
	public void editMap(@PathParam("projectUuid") String projectUuid, @PathParam("mapUuid") String mapUuid, String map) {
		service.editMap(projectUuid, mapUuid, map);
	}
	
	@DELETE
	@Path("{projectUuid}/maps/{mapUuid}")
	public void deleteMap(@PathParam("projectUuid") String projectUuid, @PathParam("mapUuid") String mapUuid) {
		service.deleteMap(projectUuid, mapUuid);
	}
	
	@DELETE
	@Path("{projectUuid}")
	public void deleteProject(@PathParam("projectUuid") String uuid) {
		service.deleteProject(uuid);
	}

	@DELETE
	@Path("{project}/maps/{map}/analysis/{analysis}")
	public void deleteAnalysis(@PathParam("project") String project, @PathParam("map") String map,
			@PathParam("analysis") String analysis) {
		Project p = service.retrieveProject(project);
		p.getMap(map).removeAnalysis(analysis);
	}
}
