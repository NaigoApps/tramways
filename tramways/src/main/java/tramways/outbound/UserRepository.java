package tramways.outbound;

import java.util.List;

import tramways.model.persistable.users.User;

public interface UserRepository {
	
	public User create(User u);	
	public User findByUsername(String user);
	public User findByUuid(String uuid);
	public List<User> findAll();
	public void deleteByUuid(String userUuid);
	
}
