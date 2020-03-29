package tramways.inbound.impl;

import tramways.core.model.persistable.users.User;
import tramways.dto.mappers.UserMapper;
import tramways.inbound.RestUtils;
import tramways.inbound.api.NotFoundException;
import tramways.inbound.api.UsersApiService;
import tramways.inbound.model.BooleanWrapper;
import tramways.inbound.model.StringWrapper;
import tramways.inbound.model.UserRequest;
import tramways.inbound.model.UserRole;
import tramways.outbound.ProjectRepository;
import tramways.outbound.UserRepository;
import tramways.services.TokenManager;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.HashMap;
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
        repository.create(user);
        return RestUtils.created("/users/" + user.getUuid(), mapper.map(user));
    }

    @Override
    public Response deleteUser(String id, SecurityContext securityContext) {
        projectRepository.deleteByUser(id);
        repository.deleteByUuid(id);
        return Response.ok().build();
    }

    @Override
    public Response editRoles(String id, List<UserRole> userRole, SecurityContext securityContext) {
        return editUser(id, user -> user.assignRoles(userRole.stream()
                .map(mapper::map)
                .collect(Collectors.toSet())));
    }

    @Override
    public Response enableUser(String id, BooleanWrapper value, SecurityContext securityContext) {
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
    public Response login(UserRequest userRequest, SecurityContext securityContext) throws NotFoundException {
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
        return editUser(id, user -> user.assignPassword(value.getValue()));
    }
}
