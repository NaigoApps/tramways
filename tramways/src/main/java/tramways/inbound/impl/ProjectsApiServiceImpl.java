package tramways.inbound.impl;

import tramways.core.model.persistable.projects.Project;
import tramways.inbound.api.ProjectsApiService;
import tramways.inbound.model.CreateProjectRequest;
import tramways.outbound.ProjectRepository;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.net.URI;

public class ProjectsApiServiceImpl implements ProjectsApiService {

    @Inject
    private ProjectRepository repository;

    @Override
    public Response projectsGet(String user, SecurityContext securityContext) throws NotFoundException {
        return null;
    }

    @Override
    public Response projectsPost(CreateProjectRequest body, SecurityContext securityContext) throws NotFoundException {
        Project project = new Project();
        project.setName(body.getName());
        repository.create(project);
        return Response.created(URI.create("uuid")).build();
    }
}
