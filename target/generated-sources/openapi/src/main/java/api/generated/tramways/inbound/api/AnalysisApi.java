package tramways.inbound.api;

import tramways.inbound.model.*;
import tramways.inbound.api.AnalysisApiService;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import tramways.inbound.model.AnalysisRequest;
import tramways.inbound.model.AnalysisResponse;
import tramways.inbound.model.AnalysisType;

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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2022-03-15T23:51:59.568152500+01:00[Europe/Berlin]")
public class AnalysisApi  {

    @Inject AnalysisApiService service;

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
    @POST
    @Path("/launch")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Launch selected analysis", notes = "", response = AnalysisResponse.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "bearerAuth")
    }, tags={ "analysis", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Ok", response = AnalysisResponse.class) })
    public Response launchAnalysis(@ApiParam(value = "" ) @Valid AnalysisRequest analysisRequest,@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.launchAnalysis(analysisRequest,securityContext);
    }
}
