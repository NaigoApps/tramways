package tramways.inbound.impl;

import tramways.core.model.persistable.projects.Project;
import tramways.core.model.persistable.projects.RoadMap;
import tramways.core.model.persistable.users.User;
import tramways.core.model.properties.Properties;
import tramways.dto.mappers.Json2RoadMapMapper;
import tramways.dto.mappers.ProjectMapper;
import tramways.dto.mappers.RoadMapMapper;
import tramways.inbound.RestUtils;
import tramways.inbound.api.NotFoundException;
import tramways.inbound.api.ProjectsApiService;
import tramways.inbound.model.CreateMapRequest;
import tramways.inbound.model.CreateProjectRequest;
import tramways.inbound.model.Lane;
import tramways.inbound.model.RelevantPoint;
import tramways.inbound.model.RoadMapContent;
import tramways.inbound.model.UpdateMapRequest;
import tramways.inbound.model.UpdateProjectRequest;
import tramways.outbound.ProjectRepository;
import tramways.outbound.UserRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

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
        Project project = repository.findById(projectId);
        tramways.core.model.persistable.projects.RoadMap newMap = new tramways.core.model.persistable.projects.RoadMap();
        newMap.setName(request.getMap().getName());
        createDemoMap(request);
        newMap.setMap(jsonRoadMapMapper.map(request.getMap().getContent()));
        project.addMap(newMap);
        return RestUtils.created("projects/" + projectId + "/maps/" + newMap.getUuid(), roadMapMapper.map(newMap));
    }

    private void createDemoMap(CreateMapRequest request) {
        RelevantPoint p0 = new RelevantPoint();
        p0.setCategory(RelevantPoint.class.getSimpleName());
        p0.setId("P0");
        p0.setConfigurableType(RelevantPoint.class.getSimpleName());
        p0.getProps().add(Properties.decimalProperty("x", BigDecimal.valueOf(0)));
        p0.getProps().add(Properties.decimalProperty("y", BigDecimal.valueOf(0)));
        RelevantPoint p1 = new RelevantPoint();
        p1.setCategory(RelevantPoint.class.getSimpleName());
        p1.setId("P1");
        p1.setConfigurableType(RelevantPoint.class.getSimpleName());
        p1.getProps().add(Properties.decimalProperty("x", BigDecimal.valueOf(1)));
        p1.getProps().add(Properties.decimalProperty("y", BigDecimal.valueOf(1)));
        Lane l1 = new Lane();
        l1.setCategory(Lane.class.getSimpleName());
        l1.setConfigurableType(Lane.class.getSimpleName());
        l1.setSourceId(p0.getId());
        l1.setDestinationId(p1.getId());

        RoadMapContent content = new RoadMapContent();
        content.setPoints(Arrays.asList(p0, p1));
        content.setLanes(Collections.singletonList(l1));
        request.getMap().setContent(content);
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
        Project project = repository.findById(projectId);
        project.removeMap(mapId);
        repository.deleteMap(mapId);
        return RestUtils.ok("Map deleted");
    }

    @Override
    public Response getProject(String id, SecurityContext securityContext) throws NotFoundException {
        return RestUtils.ok(mapper.map(repository.findById(id)));
    }

    @Override
    public Response updateProject(String id, UpdateProjectRequest request, SecurityContext securityContext) throws NotFoundException {
        Project project = repository.findById(id);
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
        return RestUtils.ok(roadMapMapper.map(repository.findById(projectId).getMap(mapId)));
    }

    @Override
    public Response getProjects(SecurityContext securityContext) throws NotFoundException {
        User currentUser = getCurrentUser(securityContext);
        return RestUtils.ok(mapper.description(repository.findByUser(currentUser.getUuid())));
    }

    @Override
    public Response updateMap(String projectId, String mapId, UpdateMapRequest request, SecurityContext securityContext) throws NotFoundException {
        RoadMap map = repository.findById(projectId).getMap(mapId);
        map.setName(request.getMap().getName());
        map.setMap(jsonRoadMapMapper.map(request.getMap().getContent()));
        return RestUtils.ok(roadMapMapper.map(map));
    }


    private User getCurrentUser(SecurityContext securityContext) {
        return userRepository.findByUsername(securityContext.getUserPrincipal().getName());
    }
}
