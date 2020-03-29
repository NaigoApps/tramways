package tramways.services;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class RequestSession {

	private String loggedUserUuid;

	public String getLoggedUserUuid() {
		return loggedUserUuid;
	}

	public void setLoggedUserUuid(String loggedUserUuid) {
		this.loggedUserUuid = loggedUserUuid;
	}

}
