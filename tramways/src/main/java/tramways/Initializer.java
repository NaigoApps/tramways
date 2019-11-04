package tramways;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.slf4j.LoggerFactory;

import tramways.exceptions.DuplicateUserException;
import tramways.inbound.UserService;
import tramways.model.auth.Role;
import tramways.model.persistable.users.User;

@Startup
@Singleton
public class Initializer {

	@Inject
	private UserService service;
	
	@PostConstruct
	public void init() {
		if (!service.exists("admin")) {
			try {
				User admin = service.register("admin", "password");
				admin.grantRole(Role.ADMIN);
			} catch (DuplicateUserException e) {
				LoggerFactory.getLogger(getClass()).error("Couldn't create admin user", e);
			}
		}
	}
}
