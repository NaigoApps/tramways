package tramways.exceptions;

public class DuplicatedUserException extends Exception {

	public DuplicatedUserException(String username) {
		super("User " + username + " already exists");
	}

}
