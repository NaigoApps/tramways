package tramways.inbound;

import java.util.List;

import tramways.dto.UserDto;
import tramways.exceptions.DuplicateUserException;
import tramways.exceptions.LoginException;
import tramways.model.persistable.users.User;

public interface UserService {

	public User register(String username, String password) throws DuplicateUserException;
	public User findUser(String uuid);
	public List<User> findAll();
	public User login(String username, String password) throws LoginException;
	public void logout();
	public void resetPassword(String oldPassword, String newPassword);
	public User getSessionData();
	public boolean exists(String username);
	public void deleteUser(String userUuid);
	public User updateUser(String uuid, UserDto dto);
	
}
