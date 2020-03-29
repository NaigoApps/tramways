package tramways;

import tramways.core.model.persistable.users.Role;
import tramways.core.model.persistable.users.User;
import tramways.outbound.UserRepository;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Startup
@Singleton
public class Initializer {

    @Inject
    private UserRepository repository;

    @PostConstruct
    public void init() {
        if (repository.countByRole(Role.ADMIN) == 0) {
            User admin = new User();
            admin.setUsername("admin");
            admin.assignPassword("password");
            admin.grantRole(Role.ADMIN);
            repository.create(admin);
        }
    }
}
