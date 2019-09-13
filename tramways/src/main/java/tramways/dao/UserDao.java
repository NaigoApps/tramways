package tramways.dao;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;

import tramways.model.auth.User;
import tramways.model.auth.User_;

public class UserDao extends AbstractDao<User> {

	public User findByUsername(String username) {
		CriteriaQuery<User> query = query();
		query.where(cb().equal(query.from(User.class).get(User_.username), username));
		try {
			return getEntityManager().createQuery(query).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}

}
