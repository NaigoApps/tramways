package tramways;

import java.security.NoSuchAlgorithmException;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.slf4j.LoggerFactory;

import tramways.services.UserService;

@Startup
@Singleton
public class Initializer {

	@Inject
	private UserService service;

	@PostConstruct
	public void init() {
		if (service.findByUsername("admin") == null) {
			try {
				service.register("admin", "password");
			} catch (NoSuchAlgorithmException e) {
				LoggerFactory.getLogger(getClass()).error("Couldn't create admin user");
			}
		}
	}
}
