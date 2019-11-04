package tramways.rs;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import tramways.dto.LoginData;
import tramways.dto.UserDto;
import tramways.exceptions.DuplicateUserException;
import tramways.inbound.UserService;
import tramways.mapper.UserMapper;
import tramways.model.auth.Role;
import tramways.model.persistable.users.User;
import tramways.rs.annotations.Roles;

@Path("users")
@Transactional
@Roles({ Role.ADMIN })
public class UserRS {

	@Inject
	private UserService service;

	@Inject
	private UserMapper mapper;

	@POST
	@Path("register")
	@Roles(Role.ADMIN)
	public UserDto register(LoginData data) throws DuplicateUserException {
		User user = service.register(data.getUsername(), data.getPassword());
		if (user != null) {
			return mapper.map(user);
		} else {
			throw new InternalServerErrorException("Couldn't register user");
		}
	}
	
	@PUT
	@Path("{userUuid}")
	public UserDto updateUser(@PathParam("userUuid") String uuid, UserDto dto) {
		return mapper.map(service.updateUser(uuid, dto));
	}
	
	@GET
	public List<UserDto> listUsers() {
		return service.findAll().stream().map(mapper::map)
				.collect(Collectors.toList());
	}

	@GET	
	@Path("{userUuid}")
	public UserDto getUser(@PathParam("userUuid") String userUuid) {
		return mapper.map(service.findUser(userUuid));
	}

	@DELETE
	@Path("{userUuid}")
	public void deleteUser(@PathParam("userUuid") String userUuid) {
		service.deleteUser(userUuid);
	}

}
