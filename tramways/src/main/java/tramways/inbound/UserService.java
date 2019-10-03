package tramways.inbound;

import tramways.exceptions.DuplicateUserException;
import tramways.exceptions.LoginException;
import tramways.model.auth.User;

public interface UserService {

	public User register(String username, String password) throws DuplicateUserException;
	public User login(String username, String password) throws LoginException;
	public void logout();
	public void resetPassword(String oldPassword, String newPassword);
	public User getSessionData();
	public boolean userExists(String username);
	
}
