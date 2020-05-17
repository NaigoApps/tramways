package tramways.inbound.api;

import tramways.inbound.model.*;
import tramways.inbound.api.AnalysisApiService;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import tramways.inbound.model.AnalysisLaunchRequest;
import tramways.inbound.model.AnalysisParamsRequest;
import tramways.inbound.model.AnalysisType;
import tramways.inbound.model.Property;
import tramways.inbound.model.StringWrapper;

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

@Path("/analysis")


@io.swagger.annotations.Api(description = "the analysis API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-05-08T22:58:22.447039+02:00[Europe/Berlin]")
public class AnalysisApi  {

    @Inject AnalysisApiService service;

    @GET
    @Path("/parameters")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Gets analysis parameters for chosen type", notes = "", response = Property.class, responseContainer = "List", authorizations = {
        @io.swagger.annotations.Authorization(value = "bearerAuth")
    }, tags={ "analysis", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Ok", response = Property.class, responseContainer = "List") })
    public Response getAnalysisParams(@ApiParam(value = "" ) @Valid AnalysisParamsRequest analysisParamsRequest,@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.getAnalysisParams(analysisParamsRequest,securityContext);
    }
    @GET
    @Path("/available")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Gets available analysis for map", notes = "", response = AnalysisType.class, responseContainer = "List", authorizations = {
        @io.swagger.annotations.Authorization(value = "bearerAuth")
    }, tags={ "analysis", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Ok", response = AnalysisType.class, responseContainer = "List") })
    public Response getAvailableAnalysis( @NotNull  @QueryParam("projectId") String projectId, @NotNull  @QueryParam("mapId") String mapId,@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.getAvailableAnalysis(projectId,mapId,securityContext);
    }
    @GET
    @Path("/launch")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Launch selected analysis", notes = "", response = StringWrapper.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "bearerAuth")
    }, tags={ "analysis", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Request successful", response = StringWrapper.class) })
    public Response launchAnalysis(@ApiParam(value = "" ) @Valid AnalysisLaunchRequest analysisLaunchRequest,@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.launchAnalysis(analysisLaunchRequest,securityContext);
    }
}
