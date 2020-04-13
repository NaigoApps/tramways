package tramways.inbound.impl;

import tramways.core.model.persistable.users.User;
import tramways.dto.mappers.UserMapper;
import tramways.inbound.RestUtils;
import tramways.inbound.api.NotFoundException;
import tramways.inbound.api.UsersApiService;
import tramways.inbound.model.BooleanWrapper;
import tramways.inbound.model.ChangePasswordRequest;
import tramways.inbound.model.LoginRequest;
import tramways.inbound.model.StringWrapper;
import tramways.inbound.model.UserRequest;
import tramways.inbound.model.UserRole;
import tramways.outbound.ProjectRepository;
import tramways.outbound.UserRepository;
import tramways.services.TokenManager;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Transactional
public class UsersApiServiceImpl implements UsersApiService {

    @Inject
    private UserRepository repository;

    @Inject
    private ProjectRepository projectRepository;

    @Inject
    private UserMapper mapper;

    @Inject
    private TokenManager tokenManager;

    @Override
    public Response createUser(UserRequest userRequest, SecurityContext securityContext) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.assignPassword(userRequest.getPassword());
        user.assignRoles(new HashSet<>(mapper.toModel(userRequest.getRoles())));
        repository.create(user);
        return RestUtils.created("/users/" + user.getUuid(), mapper.map(user));
    }

    @Override
    public Response deleteUser(String id, SecurityContext securityContext) {
        if(isCurrentUser(id, securityContext)){
           throw new BadRequestException("Cannot delete current user");
        }
        projectRepository.deleteByUser(id);
        repository.deleteByUuid(id);
        return Response.ok().build();
    }

    @Override
    public Response editPassword(String id, ChangePasswordRequest password, SecurityContext securityContext) throws NotFoundException {
        return editUser(id, user -> {
            if(user.passwordMatches(password.getOldPassword())){
                user.assignPassword(password.getNewPassword());
            }else{
                throw new BadRequestException("Old password not correct");
            }
        });
    }

    @Override
    public Response editRoles(String id, List<UserRole> userRole, SecurityContext securityContext) {
        if(isCurrentUser(id, securityContext)){
            throw new BadRequestException("Cannot edit current user");
        }
        return editUser(id, user -> user.assignRoles(userRole.stream()
                .map(UserMapper::toModel)
                .collect(Collectors.toSet())));
    }

    @Override
    public Response enableUser(String id, BooleanWrapper value, SecurityContext securityContext) {
        if(isCurrentUser(id, securityContext)){
            throw new BadRequestException("Cannot edit current user");
        }
        return editUser(id, user -> user.setEnabled(Boolean.TRUE.equals(value.getValue())));
    }

    private Response editUser(String uuid, Consumer<User> editor) {
        User user = repository.findByUuid(uuid);
        editor.accept(user);
        return RestUtils.ok(mapper.map(user));
    }

    @Override
    public Response getUser(String id, SecurityContext securityContext) {
        return editUser(id, user -> {
        });
    }

    @Override
    public Response getUsers(SecurityContext securityContext) {
        return RestUtils.ok(mapper.map(repository.findAll()));
    }

    @Override
    public Response logged(SecurityContext securityContext)  {
        User user = repository.findByUsername(securityContext.getUserPrincipal().getName());
        return RestUtils.ok(mapper.map(user));
    }

    @Override
    public Response login(LoginRequest userRequest, SecurityContext securityContext) throws NotFoundException {
        User user;
        if ((user = repository.findByUsername(userRequest.getUsername())) != null &&
                user.passwordMatches(userRequest.getPassword())) {
            String token = tokenManager.requestToken(user);
            Map<String, String> headers = new HashMap<>();
            headers.put(HttpHeaders.AUTHORIZATION, "Bearer " + token);
            return RestUtils.ok("Login successful", headers);
        }
        return RestUtils.unauthorized("Login failed");
    }

    @Override
    public Response resetUser(String id, StringWrapper value, SecurityContext securityContext) {
        if(isCurrentUser(id, securityContext)){
            throw new BadRequestException("Cannot delete current user");
        }
        return editUser(id, user -> user.assignPassword(value.getValue()));
    }

    private boolean isCurrentUser(String id, SecurityContext securityContext) {
        return repository.findByUsername(securityContext.getUserPrincipal().getName()).getUuid().equals(id);
    }
}
