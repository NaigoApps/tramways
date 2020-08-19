package tramways.inbound.api;

import tramways.inbound.api.*;
import tramways.inbound.model.*;


import tramways.inbound.model.BooleanWrapper;
import tramways.inbound.model.ChangePasswordRequest;
import tramways.inbound.model.ErrorWrapper;
import java.util.List;
import tramways.inbound.model.LoginRequest;
import tramways.inbound.model.StringWrapper;
import tramways.inbound.model.User;
import tramways.inbound.model.UserRequest;
import tramways.inbound.model.UserRole;

import java.util.List;
import tramways.inbound.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-08-19T18:54:09.902175+02:00[Europe/Rome]")
public interface UsersApiService {
      Response createUser(UserRequest userRequest,SecurityContext securityContext)
      throws NotFoundException;
      Response deleteUser(String id,SecurityContext securityContext)
      throws NotFoundException;
      Response editPassword(String id,ChangePasswordRequest changePasswordRequest,SecurityContext securityContext)
      throws NotFoundException;
      Response editRoles(String id,List<UserRole> userRole,SecurityContext securityContext)
      throws NotFoundException;
      Response enableUser(String id,BooleanWrapper booleanWrapper,SecurityContext securityContext)
      throws NotFoundException;
      Response getUser(String id,SecurityContext securityContext)
      throws NotFoundException;
      Response getUsers(SecurityContext securityContext)
      throws NotFoundException;
      Response logged(SecurityContext securityContext)
      throws NotFoundException;
      Response login(LoginRequest loginRequest,SecurityContext securityContext)
      throws NotFoundException;
      Response resetUser(String id,StringWrapper stringWrapper,SecurityContext securityContext)
      throws NotFoundException;
}
