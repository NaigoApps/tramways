package tramways.services;

import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;

import tramways.dao.UserDao;
import tramways.model.auth.User;

public class UserService {

	@Inject
	private UserDao dao;
	
	public User register(String username, String password) throws NoSuchAlgorithmException {
		User user = new User();
		user.setUsername(username);
		user.assignPassword(password);
		dao.persist(user);
		return user;
	}
	
	public User findByUsername(String user) {
		return dao.findByUsername(user);
	}
	
	public User findByUuid(String uuid) {
		return dao.findByUuid(uuid);
	}
	
	public boolean resetPassword(String user, String oldP, String newP) throws NoSuchAlgorithmException {
		User u = dao.findByUsername(user);
		if(u.passwordMatches(oldP)) {			
			u.assignPassword(newP);
			return true;
		}
		return false;
	}
	
}
