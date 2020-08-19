package tramways.core.model.persistable.users;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ extends tramways.core.model.persistable.BaseEntity_ {

	public static volatile SingularAttribute<User, byte[]> password;
	public static volatile SingularAttribute<User, byte[]> salt;
	public static volatile SetAttribute<User, Role> roles;
	public static volatile SingularAttribute<User, Boolean> enabled;
	public static volatile SingularAttribute<User, String> username;

	public static final String PASSWORD = "password";
	public static final String SALT = "salt";
	public static final String ROLES = "roles";
	public static final String ENABLED = "enabled";
	public static final String USERNAME = "username";

}

