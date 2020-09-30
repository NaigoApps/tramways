package tramways.inbound.api;

import tramways.inbound.model.*;
import tramways.inbound.api.ConfigurationsApiService;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import tramways.inbound.model.ConfigurableCategory;
import tramways.inbound.model.CreateConfigurationRequest;
import tramways.inbound.model.ItemConfiguration;
import tramways.inbound.model.Property;
import tramways.inbound.model.StringWrapper;
import tramways.inbound.model.UpdateConfigurationRequest;

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

@Path("/configurations")


@io.swagger.annotations.Api(description = "the configurations API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-09-30T22:40:17.034366700+02:00[Europe/Berlin]")
public class ConfigurationsApi  {

    @Inject ConfigurationsApiService service;

    @POST
    @Path("/{category}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Adds configuration to a category", notes = "", response = StringWrapper.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "bearerAuth")
    }, tags={ "configurations", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Request successful", response = StringWrapper.class) })
    public Response addConfiguration( @PathParam("category") String category,@ApiParam(value = "" ) @Valid CreateConfigurationRequest createConfigurationRequest,@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.addConfiguration(category,createConfigurationRequest,securityContext);
    }
    @PUT
    @Path("/{configurationId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Edits a configuration", notes = "", response = StringWrapper.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "bearerAuth")
    }, tags={ "configurations", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Request successful", response = StringWrapper.class) })
    public Response editConfiguration( @PathParam("configurationId") String configurationId,@ApiParam(value = "" ) @Valid UpdateConfigurationRequest updateConfigurationRequest,@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.editConfiguration(configurationId,updateConfigurationRequest,securityContext);
    }
    @GET
    @Path("/categories")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Gets all configuration groups", notes = "", response = ConfigurableCategory.class, responseContainer = "List", authorizations = {
        @io.swagger.annotations.Authorization(value = "bearerAuth")
    }, tags={ "configurations", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "List of configuration groups", response = ConfigurableCategory.class, responseContainer = "List") })
    public Response getConfigurationCategories(@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.getConfigurationCategories(securityContext);
    }
    @GET
    
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Get configurations given category", notes = "", response = ItemConfiguration.class, responseContainer = "List", authorizations = {
        @io.swagger.annotations.Authorization(value = "bearerAuth")
    }, tags={ "configurations", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "List of configurations", response = ItemConfiguration.class, responseContainer = "List") })
    public Response getConfigurations(  @QueryParam("category") String category,@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.getConfigurations(category,securityContext);
    }
    @GET
    @Path("/suggestions")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Get properties given category", notes = "", response = Property.class, responseContainer = "List", authorizations = {
        @io.swagger.annotations.Authorization(value = "bearerAuth")
    }, tags={ "configurations", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "List of properties", response = Property.class, responseContainer = "List") })
    public Response getPropertiesSuggestions(  @QueryParam("category") String category,@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.getPropertiesSuggestions(category,securityContext);
    }
    @DELETE
    @Path("/{configurationId}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Removes a configuration", notes = "", response = StringWrapper.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "bearerAuth")
    }, tags={ "configurations", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Request successful", response = StringWrapper.class) })
    public Response removeConfiguration( @PathParam("configurationId") String configurationId,@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.removeConfiguration(configurationId,securityContext);
    }
}
