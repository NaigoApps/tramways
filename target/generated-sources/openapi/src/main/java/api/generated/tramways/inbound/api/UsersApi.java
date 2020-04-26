package tramways.inbound.api;

import tramways.inbound.model.*;
import tramways.inbound.api.UsersApiService;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import tramways.inbound.model.BooleanWrapper;
import tramways.inbound.model.ChangePasswordRequest;
import tramways.inbound.model.ErrorWrapper;
import java.util.List;
import tramways.inbound.model.LoginRequest;
import tramways.inbound.model.StringWrapper;
import tramways.inbound.model.User;
import tramways.inbound.model.UserRequest;
import tramways.inbound.model.UserRole;

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

@Path("/users")


@io.swagger.annotations.Api(description = "the users API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-04-26T14:37:23.351990800+02:00[Europe/Berlin]")
public class UsersApi  {

    @Inject UsersApiService service;

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Creates a new user", notes = "", response = User.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "bearerAuth")
    }, tags={ "users", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Created", response = User.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "You are not authorized", response = Void.class) })
    public Response createUser(@ApiParam(value = "" ) @Valid UserRequest userRequest,@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.createUser(userRequest,securityContext);
    }
    @DELETE
    @Path("/{id}")
    
    
    @io.swagger.annotations.ApiOperation(value = "Deletes a user", notes = "", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "bearerAuth")
    }, tags={ "users", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = Void.class) })
    public Response deleteUser( @PathParam("id") String id,@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.deleteUser(id,securityContext);
    }
    @PUT
    @Path("/{id}/password")
    @Consumes({ "application/json" })
    
    @io.swagger.annotations.ApiOperation(value = "Edit user's password", notes = "", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "bearerAuth")
    }, tags={ "users", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = Void.class) })
    public Response editPassword( @PathParam("id") String id,@ApiParam(value = "" ) @Valid ChangePasswordRequest changePasswordRequest,@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.editPassword(id,changePasswordRequest,securityContext);
    }
    @PUT
    @Path("/{id}/roles")
    @Consumes({ "application/json" })
    
    @io.swagger.annotations.ApiOperation(value = "Edit user's roles", notes = "", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "bearerAuth")
    }, tags={ "users", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = Void.class) })
    public Response editRoles( @PathParam("id") String id,@ApiParam(value = "" ) @Valid List<UserRole> userRole,@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.editRoles(id,userRole,securityContext);
    }
    @PUT
    @Path("/{id}/enable")
    @Consumes({ "application/json" })
    
    @io.swagger.annotations.ApiOperation(value = "Enable or disable a user", notes = "", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "bearerAuth")
    }, tags={ "users", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Ok", response = Void.class) })
    public Response enableUser( @PathParam("id") String id,@ApiParam(value = "" ) @Valid BooleanWrapper booleanWrapper,@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.enableUser(id,booleanWrapper,securityContext);
    }
    @GET
    @Path("/{id}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Gets a user", notes = "", response = User.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "bearerAuth")
    }, tags={ "users", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "User", response = User.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "User not found", response = ErrorWrapper.class) })
    public Response getUser( @PathParam("id") String id,@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.getUser(id,securityContext);
    }
    @GET
    
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Gets all users", notes = "", response = User.class, responseContainer = "List", authorizations = {
        @io.swagger.annotations.Authorization(value = "bearerAuth")
    }, tags={ "users", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "List of users", response = User.class, responseContainer = "List") })
    public Response getUsers(@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.getUsers(securityContext);
    }
    @GET
    @Path("/logged")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Gets logged user", notes = "", response = User.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "bearerAuth")
    }, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Ok", response = User.class) })
    public Response logged(@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.logged(securityContext);
    }
    @POST
    @Path("/login")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Logs a user in", notes = "", response = StringWrapper.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Request successful", response = StringWrapper.class) })
    public Response login(@ApiParam(value = "" ) @Valid LoginRequest loginRequest,@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.login(loginRequest,securityContext);
    }
    @PUT
    @Path("/{id}/reset")
    @Consumes({ "application/json" })
    
    @io.swagger.annotations.ApiOperation(value = "Reset user's password", notes = "", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "bearerAuth")
    }, tags={ "users", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = Void.class) })
    public Response resetUser( @PathParam("id") String id,@ApiParam(value = "" ) @Valid StringWrapper stringWrapper,@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.resetUser(id,stringWrapper,securityContext);
    }
}
