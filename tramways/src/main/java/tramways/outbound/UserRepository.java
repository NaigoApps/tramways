package tramways.outbound;

import tramways.model.auth.User;

public interface UserRepository {
	
	public User create(User u);	
	public User findByUsername(String user);
	public User findByUuid(String uuid);
	
}
