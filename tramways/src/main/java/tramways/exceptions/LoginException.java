package tramways.exceptions;

public class LoginException extends Exception {

	public LoginException() {
		super("Username or password are not correct");
	}
	
}
