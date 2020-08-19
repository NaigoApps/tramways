package tramways.inbound.api;

import tramways.inbound.model.*;
import tramways.inbound.api.ProjectsApiService;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import tramways.inbound.model.Analysis;
import tramways.inbound.model.CreateMapRequest;
import tramways.inbound.model.CreateProjectRequest;
import tramways.inbound.model.Project;
import tramways.inbound.model.ProjectDescription;
import tramways.inbound.model.RoadMap;
import tramways.inbound.model.StringWrapper;
import tramways.inbound.model.UpdateMapRequest;
import tramways.inbound.model.UpdateProjectRequest;

import java.util.Map;
import java.util.List;
import tramways.inbound.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.inject.Inject;

import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/projects")


@io.swagger.annotations.Api(description = "the projects API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-08-19T18:54:09.902175+02:00[Europe/Rome]")
public class ProjectsApi  {

    @Inject ProjectsApiService service;

    @POST
    @Path("/{projectId}/maps")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Creates a map", notes = "", response = RoadMap.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "bearerAuth")
    }, tags={ "projects", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Ok", response = RoadMap.class) })
    public Response createMap( @PathParam("projectId") String projectId,@ApiParam(value = "" ) @Valid CreateMapRequest createMapRequest,@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.createMap(projectId,createMapRequest,securityContext);
    }
    @POST
    
    @Consumes({ "application/json" })
    
    @io.swagger.annotations.ApiOperation(value = "Creates a new project", notes = "", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "bearerAuth")
    }, tags={ "projects", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Ok", response = Void.class) })
    public Response createProject(@ApiParam(value = "" ) @Valid CreateProjectRequest createProjectRequest,@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.createProject(createProjectRequest,securityContext);
    }
    @DELETE
    @Path("/{projectId}/maps/{mapId}/{analysisId}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Deletes an analysis", notes = "", response = StringWrapper.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "bearerAuth")
    }, tags={ "projects", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Request successful", response = StringWrapper.class) })
    public Response deleteAnalysis( @PathParam("projectId") String projectId, @PathParam("mapId") String mapId, @PathParam("analysisId") String analysisId,@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.deleteAnalysis(projectId,mapId,analysisId,securityContext);
    }
    @DELETE
    @Path("/{projectId}/maps/{mapId}")
    
    
    @io.swagger.annotations.ApiOperation(value = "Deletes a map", notes = "", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "bearerAuth")
    }, tags={ "projects", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Ok", response = Void.class) })
    public Response deleteMap( @PathParam("projectId") String projectId, @PathParam("mapId") String mapId,@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.deleteMap(projectId,mapId,securityContext);
    }
    @DELETE
    @Path("/{id}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Deletes a project", notes = "", response = StringWrapper.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "bearerAuth")
    }, tags={ "projects", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Request successful", response = StringWrapper.class) })
    public Response deleteProject( @PathParam("id") String id,@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.deleteProject(id,securityContext);
    }
    @GET
    @Path("/{projectId}/maps/{mapId}/{analysisId}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Gets an analysis", notes = "", response = Analysis.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "bearerAuth")
    }, tags={ "projects", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Ok", response = Analysis.class) })
    public Response getAnalysis( @PathParam("projectId") String projectId, @PathParam("mapId") String mapId, @PathParam("analysisId") String analysisId,@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.getAnalysis(projectId,mapId,analysisId,securityContext);
    }
    @GET
    @Path("/{projectId}/maps/{mapId}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Gets a map", notes = "", response = RoadMap.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "bearerAuth")
    }, tags={ "projects", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Ok", response = RoadMap.class) })
    public Response getMap( @PathParam("projectId") String projectId, @PathParam("mapId") String mapId,@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.getMap(projectId,mapId,securityContext);
    }
    @GET
    @Path("/{id}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Gets project", notes = "", response = Project.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "bearerAuth")
    }, tags={ "projects", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Ok", response = Project.class) })
    public Response getProject( @PathParam("id") String id,@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.getProject(id,securityContext);
    }
    @GET
    
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Gets user projects", notes = "", response = ProjectDescription.class, responseContainer = "List", authorizations = {
        @io.swagger.annotations.Authorization(value = "bearerAuth")
    }, tags={ "projects", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Ok", response = ProjectDescription.class, responseContainer = "List") })
    public Response getProjects(@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.getProjects(securityContext);
    }
    @PUT
    @Path("/{projectId}/maps/{mapId}")
    @Consumes({ "application/json" })
    
    @io.swagger.annotations.ApiOperation(value = "Updates a map", notes = "", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "bearerAuth")
    }, tags={ "projects", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Ok", response = Void.class) })
    public Response updateMap( @PathParam("projectId") String projectId, @PathParam("mapId") String mapId,@ApiParam(value = "" ) @Valid UpdateMapRequest updateMapRequest,@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.updateMap(projectId,mapId,updateMapRequest,securityContext);
    }
    @PUT
    @Path("/{id}")
    @Consumes({ "application/json" })
    
    @io.swagger.annotations.ApiOperation(value = "Updates a project", notes = "", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "bearerAuth")
    }, tags={ "projects", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Ok", response = Void.class) })
    public Response updateProject( @PathParam("id") String id,@ApiParam(value = "" ) @Valid UpdateProjectRequest updateProjectRequest,@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.updateProject(id,updateProjectRequest,securityContext);
    }
}
