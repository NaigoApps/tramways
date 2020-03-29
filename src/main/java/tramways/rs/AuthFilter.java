package tramways.rs;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import tramways.core.model.persistable.users.Role;
import tramways.core.model.persistable.users.User;
import tramways.inbound.api.UsersApiService;
import tramways.outbound.UserRepository;
import tramways.rs.annotations.Roles;
import tramways.rs.annotations.Unsecure;
import tramways.services.RequestSession;
import tramways.services.TokenManager;

//FIXME
//@Provider
public class AuthFilter implements ContainerRequestFilter, ContainerResponseFilter {

	@Context
	private ResourceInfo resourceInfo;

	@Inject
	private RequestSession authService;

	@Inject
	private UsersApiService userService;

	@Inject
	private TokenManager tokenManager;

	@Inject
	private UserRepository userRepository;

	@Override
	@Transactional
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if (isUnsecure()) {
			return;
		}

		User user = null;
		if ((user = extractUser(requestContext)) != null) {
			requestContext.setSecurityContext(new TramwaysSecurityContext(user));
			if (!hasPermission(user.listRoles())) {
				throw new BadRequestException("Unauthorized");
			}
		} else {
			throw new BadRequestException("Couldn't authenticate user");
		}
	}

	private boolean hasPermission(Set<Role> userRoles) {
		Roles methodAnnotation = resourceInfo.getResourceMethod().getAnnotation(Roles.class);
		Roles classAnnotation = resourceInfo.getResourceClass().getAnnotation(Roles.class);

		if (methodAnnotation != null) {
			return Arrays.stream(methodAnnotation.value()).anyMatch(userRoles::contains);
		}

		if (classAnnotation != null) {
			return Arrays.stream(classAnnotation.value()).anyMatch(userRoles::contains);
		}

		return true;
	}

	private boolean isUnsecure() {
		return resourceInfo.getResourceMethod().isAnnotationPresent(Unsecure.class);
	}

	private User extractUser(ContainerRequestContext context) {
		String authorizationHeader = context.getHeaderString(HttpHeaders.AUTHORIZATION);
		if (authorizationHeader != null) {
			String token = authorizationHeader.substring("Bearer".length()).trim();
			return userRepository.findByUuid(tokenManager.token2User(token));
		}
		return null;
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		if (!responseContext.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
			String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
			responseContext.getHeaders().add(HttpHeaders.AUTHORIZATION, authorizationHeader);
		}
	}

}
