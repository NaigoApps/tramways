package tramways.outbound;

import java.util.List;

import tramways.core.model.persistable.users.Role;
import tramways.core.model.persistable.users.User;

public interface UserRepository {

	User create(User u);
	User findByUsername(String user);
	User findByUuid(String uuid);
	List<User> findAll();
	void deleteByUuid(String userUuid);
	Long countByRole(Role role);
}
