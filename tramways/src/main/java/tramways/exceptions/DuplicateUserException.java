package tramways.exceptions;

public class DuplicateUserException extends Exception {

	public DuplicateUserException(String username) {
		super("User " + username + " already exists");
	}

}
