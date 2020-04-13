package tramways.inbound.api;

import tramways.inbound.api.*;
import tramways.inbound.model.*;


import tramways.inbound.model.CreateMapRequest;
import tramways.inbound.model.CreateProjectRequest;
import tramways.inbound.model.Project;
import tramways.inbound.model.ProjectDescription;
import tramways.inbound.model.RoadMap;
import tramways.inbound.model.UpdateMapRequest;
import tramways.inbound.model.UpdateProjectRequest;

import java.util.List;
import tramways.inbound.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-04-13T14:51:32.877637400+02:00[Europe/Berlin]")
public interface ProjectsApiService {
      Response createMap(String projectId,CreateMapRequest createMapRequest,SecurityContext securityContext)
      throws NotFoundException;
      Response createProject(CreateProjectRequest createProjectRequest,SecurityContext securityContext)
      throws NotFoundException;
      Response deleteMap(String projectId,String mapId,SecurityContext securityContext)
      throws NotFoundException;
      Response deleteProject(String id,SecurityContext securityContext)
      throws NotFoundException;
      Response getMap(String projectId,String mapId,SecurityContext securityContext)
      throws NotFoundException;
      Response getProject(String id,SecurityContext securityContext)
      throws NotFoundException;
      Response getProjects(SecurityContext securityContext)
      throws NotFoundException;
      Response updateMap(String projectId,String mapId,UpdateMapRequest updateMapRequest,SecurityContext securityContext)
      throws NotFoundException;
      Response updateProject(String id,UpdateProjectRequest updateProjectRequest,SecurityContext securityContext)
      throws NotFoundException;
}
