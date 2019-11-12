package tramways.inbound.impl;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;

import tramways.core.model.persistable.users.Role;
import tramways.core.model.persistable.users.User;
import tramways.dto.UserDto;
import tramways.exceptions.DuplicateUserException;
import tramways.exceptions.LoginException;
import tramways.inbound.UserService;
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
			user.grantRole(Role.CLIENT);
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
	public User updateUser(String uuid, UserDto dto) {
		User user = repository.findByUuid(uuid);
		if (user != null) {
			user.assignRoles(dto.getRoles());
			return user;
		} else {
			throw new IllegalArgumentException("User not found");
		}
	}

	@Override
	public boolean exists(String username) {
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

	@Override
	public User findUser(String uuid) {
		return repository.findByUuid(uuid);
	}

	@Override
	public List<User> findAll() {
		return repository.findAll();
	}

	@Override
	public void deleteUser(String userUuid) {
		if(session.getLoggedUserUuid().equals(userUuid)) {
			throw new BadRequestException("Impossibile eliminare l'utente corrente");
		}
		repository.deleteByUuid(userUuid);
	}

}
