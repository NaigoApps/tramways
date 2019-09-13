package tramways.rs;

import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import tramways.dto.LoginData;
import tramways.dto.UserDto;
import tramways.dto.Wrapper;
import tramways.mapper.UserMapper;
import tramways.model.auth.Role;
import tramways.model.auth.User;
import tramways.rs.annotations.Roles;
import tramways.rs.annotations.Unsecure;
import tramways.services.RequestSession;
import tramways.services.TokenManager;
import tramways.services.UserService;

@Path("auth")
@Transactional
public class AuthRestService {

	@Inject
	private RequestSession service;

	@Inject
	private UserService userService;
	
	@Inject
	private UserMapper userMapper;

	@Inject
	private TokenManager tokenManager;

	@POST
	@Path("login")
	@Unsecure
	public Response login(LoginData data) {
		User user = tokenManager.authenticate(data.getUserName(), data.getPassword());
		if(user != null) {
			service.setLoggedUserUuid(user.getUuid());
			return Response.ok()
					.header("Authorization", "Bearer " + tokenManager.requestToken(user))
					.build();
		}else {
			throw new BadRequestException("Username or password not correct");
		}
	}
	
	@GET
	@Path("logged-user")
	public UserDto getLoggedUser() {
		return userMapper.map(userService.findByUuid(service.getLoggedUserUuid()));
	}

	@POST
	@Path("register-user")
	@Roles(Role.ADMIN)
	public Wrapper<String> register(LoginData data) throws NoSuchAlgorithmException {
		User user = userService.register(data.getUserName(), data.getPassword());
		service.setLoggedUserUuid(user.getUuid());
		return new Wrapper<>(tokenManager.requestToken(user));
	}

	@GET
	@Path("reset-password")
	public void resetPassword(LoginData data) throws NoSuchAlgorithmException {
		if(!userService.resetPassword(data.getUserName(), data.getPassword(), data.getNewPassword())) {
			throw new BadRequestException("Username or password not correct");
		}
	}

}
