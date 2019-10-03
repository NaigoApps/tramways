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

import tramways.dto.ProjectDto;
import tramways.inbound.ProjectService;
import tramways.mapper.ProjectMapper;
import tramways.model.projects.Project;
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
	
	@DELETE
	@Path("{projectUuid}")
	public void deleteProject(@PathParam("projectUuid") String uuid) {
		service.deleteProject(uuid);
	}
	
}
