package tramways.rs;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Path;

import tramways.dto.mappers.UserMapper;
import tramways.inbound.api.UsersApiService;
import tramways.services.TokenManager;

@Path("auth")
@Transactional
public class AuthRestService {

	@Inject
	private UsersApiService userService;

	@Inject
	private UserMapper userMapper;

	@Inject
	private TokenManager tokenManager;

//	@POST
//	@Path("login")
//	@Unsecure
//	public Response login(LoginData data) throws LoginException {
//		User loggedUser = userService.login(data.getUsername(), data.getPassword());
//		if(loggedUser != null) {
//			return Response.ok()
//					.header("Authorization", "Bearer " + tokenManager.requestToken(loggedUser))
//					.build();
//		} else {
//			throw new LoginException();
//		}
//	}

//	@GET
//	@Path("session-info")
//	public UserDto getLoggedUser() {
//		return userMapper.map(userService.getSessionData());
//	}

//	@POST
//	@Path("reset-password")
//	public void resetPassword(LoginData data) {
//		userService.resetPassword(data.getPassword(), data.getNewPassword());
//	}

}
