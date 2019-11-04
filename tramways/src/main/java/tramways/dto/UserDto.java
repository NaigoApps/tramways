package tramways.dto;

import java.util.Set;

import tramways.model.auth.Role;

public class UserDto extends Dto {
	private String username;

	private Set<Role> roles;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
