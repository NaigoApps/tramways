package tramways;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.LoggerFactory;

import tramways.model.Configuration;
import tramways.model.properties.IntegerProperty;
import tramways.services.UserService;

@Startup
@Singleton
public class Initializer {

	@Inject
	private UserService service;
	
	@PersistenceContext(unitName = "tramways")
	private EntityManager entityManager;
	
	@PostConstruct
	public void init() {
		if (service.findByUsername("admin") == null) {
			try {
				service.register("admin", "password");
			} catch (NoSuchAlgorithmException e) {
				LoggerFactory.getLogger(getClass()).error("Couldn't create admin user");
			}
		}
		
		Configuration conf = new Configuration();
		conf.setUuid("config");
		
		IntegerProperty iProp = new IntegerProperty();
		iProp.setName("my_prop");
		iProp.setValue(5L);
		conf.setProperties(Arrays.asList(iProp));
		
		entityManager.persist(conf);
	}
}
