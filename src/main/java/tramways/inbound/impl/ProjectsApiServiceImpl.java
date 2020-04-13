package tramways.inbound.impl;

import tramways.core.model.persistable.projects.Project;
import tramways.core.model.persistable.users.User;
import tramways.dto.mappers.Json2RoadMapMapper;
import tramways.dto.mappers.ProjectMapper;
import tramways.dto.mappers.RoadMapMapper;
import tramways.inbound.RestUtils;
import tramways.inbound.api.NotFoundException;
import tramways.inbound.api.ProjectsApiService;
import tramways.inbound.model.CreateMapRequest;
import tramways.inbound.model.CreateProjectRequest;
import tramways.inbound.model.UpdateMapRequest;
import tramways.inbound.model.UpdateProjectRequest;
import tramways.outbound.ProjectRepository;
import tramways.outbound.UserRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Transactional
public class ProjectsApiServiceImpl implements ProjectsApiService {

    @Inject
    private ProjectMapper mapper;

    @Inject
    private RoadMapMapper roadMapMapper;

    @Inject
    private Json2RoadMapMapper jsonRoadMapMapper;

    @Inject
    private ProjectRepository repository;

    @Inject
    private UserRepository userRepository;

    @Override
    public Response createMap(String projectId, CreateMapRequest request, SecurityContext securityContext) throws NotFoundException {
        Project project = repository.findByUuid(projectId);
        tramways.core.model.persistable.projects.RoadMap newMap = new tramways.core.model.persistable.projects.RoadMap();
        newMap.setName(request.getName());
        newMap.setMap(jsonRoadMapMapper.map(request.getMap()));
        project.addMap(newMap);
        return RestUtils.created("projects/" + projectId + "/maps/" + newMap.getUuid(), roadMapMapper.map(newMap));
    }

    @Override
    public Response createProject(CreateProjectRequest createProjectRequest, SecurityContext securityContext) throws NotFoundException {
        Project project = new Project();
        project.setName(createProjectRequest.getName());
        project.setOwner(getCurrentUser(securityContext));
        repository.create(project);
        return RestUtils.created("projects/" + project.getUuid(), mapper.map(project));
    }

    @Override
    public Response deleteMap(String projectId, String mapId, SecurityContext securityContext) throws NotFoundException {
        return null;
    }

    @Override
    public Response getProject(String id, SecurityContext securityContext) throws NotFoundException {
        return RestUtils.ok(mapper.map(repository.findByUuid(id)));
    }

    @Override
    public Response updateProject(String id, UpdateProjectRequest request, SecurityContext securityContext) throws NotFoundException {
        Project project = repository.findByUuid(id);
        project.setName(request.getName());
        return RestUtils.ok(mapper.map(project));
    }

    @Override
    public Response deleteProject(String id, SecurityContext securityContext) throws NotFoundException {
        repository.delete(id);
        return RestUtils.ok("Project deleted");
    }

    @Override
    public Response getMap(String projectId, String mapId, SecurityContext securityContext) throws NotFoundException {
        return null;
    }

    @Override
    public Response getProjects(SecurityContext securityContext) throws NotFoundException {
        User currentUser = getCurrentUser(securityContext);
        return RestUtils.ok(mapper.description(repository.findByUser(currentUser.getUuid())));
    }

    @Override
    public Response updateMap(String projectId, String mapId, UpdateMapRequest updateMapRequest, SecurityContext securityContext) throws NotFoundException {
        return null;
    }


    private User getCurrentUser(SecurityContext securityContext) {
        return userRepository.findByUsername(securityContext.getUserPrincipal().getName());
    }
}
