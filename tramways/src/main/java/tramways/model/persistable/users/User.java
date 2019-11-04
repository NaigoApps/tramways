package tramways.model.persistable.users;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.ws.rs.InternalServerErrorException;

import tramways.model.auth.Role;
import tramways.model.persistable.BaseEntity;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

	private String username;

	private boolean toBeDeleted;

	@Lob
	private byte[] password;

	@Lob
	private byte[] salt;

	@ElementCollection
	@Enumerated(EnumType.STRING)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
	private Set<Role> roles;

	public User() {
		roles = EnumSet.noneOf(Role.class);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public byte[] getPassword() {
		return password;
	}

	public byte[] getSalt() {
		return salt;
	}

	public void assignPassword(String plainPassword) {
		MessageDigest digest = getAlgorithm();
		SecureRandom random = new SecureRandom();
		byte[] secret = new byte[16];
		random.nextBytes(secret);
		digest.update(secret);

		this.salt = secret;
		this.password = digest.digest(plainPassword.getBytes(StandardCharsets.UTF_8));
	}

	public boolean passwordMatches(String plainPassword) {
		MessageDigest digest = getAlgorithm();
		digest.update(salt);
		return Arrays.equals(password, digest.digest(plainPassword.getBytes(StandardCharsets.UTF_8)));
	}

	public boolean hasRole(Role r) {
		return roles.contains(r);
	}

	public void grantRole(Role r) {
		roles.add(r);
	}

	public void revokeRole(Role r) {
		roles.remove(r);
	}

	public Set<Role> listRoles() {
		return Collections.unmodifiableSet(roles);
	}

	public boolean isToBeDeleted() {
		return toBeDeleted;
	}

	public void setToBeDeleted(boolean toBeDeleted) {
		this.toBeDeleted = toBeDeleted;
	}

	private static MessageDigest getAlgorithm() {
		try {
			return MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new InternalServerErrorException("Algorithm not found");
		}
	}

	public void assignRoles(Set<Role> roles) {
		this.roles = new HashSet<>(roles);
	}

}
