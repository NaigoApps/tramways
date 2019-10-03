package tramways.inbound.impl;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;

import tramways.exceptions.DuplicateUserException;
import tramways.exceptions.LoginException;
import tramways.inbound.UserService;
import tramways.model.auth.User;
import tramways.outbound.UserRepository;
import tramways.services.RequestSession;

public class UserServiceImpl implements UserService {

	@Inject
	private UserRepository repository;

	@Inject
	private RequestSession session;

	public User register(String username, String password) throws DuplicateUserException {
		User existingUser = repository.findByUsername(username);
		if (existingUser == null) {
			User user = new User();
			user.setUsername(username);
			user.assignPassword(password);
			user = repository.create(user);
			return user;
		} else {
			throw new DuplicateUserException(username);
		}
	}

	@Override
	public User login(String username, String password) throws LoginException {
		User user = repository.findByUsername(username);
		if (user != null && user.passwordMatches(password)) {
			session.setLoggedUserUuid(user.getUuid());
			return user;
		} else {
			throw new LoginException();
		}
	}

	@Override
	public boolean userExists(String username) {
		return repository.findByUsername(username) != null;
	}

	@Override
	public void logout() {
		session.setLoggedUserUuid(null);
	}

	@Override
	public User getSessionData() {
		return repository.findByUuid(session.getLoggedUserUuid());
	}

	@Override
	public void resetPassword(String oldPassword, String newPassword) {
		String uuid = session.getLoggedUserUuid();
		if (uuid != null) {
			User u = repository.findByUuid(uuid);
			if (u != null) {
				if (u.passwordMatches(oldPassword)) {
					u.assignPassword(newPassword);
				} else {
					throw new BadRequestException("Old password not correct");
				}
			} else {
				throw new BadRequestException("Utente non trovato");
			}
		} else {
			throw new BadRequestException("Login non effettuato");
		}
	}

}
