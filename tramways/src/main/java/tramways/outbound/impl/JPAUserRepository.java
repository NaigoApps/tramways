package tramways.outbound.impl;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;

import tramways.dao.AbstractJPARepository;
import tramways.model.auth.User;
import tramways.model.auth.User_;
import tramways.outbound.UserRepository;

public class JPAUserRepository extends AbstractJPARepository<User> implements UserRepository {

	@Override
	public User create(User u) {
		persist(u);
		return u;
	}
	
	@Override
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
	public User findByUuid(String uuid) {
		CriteriaQuery<User> query = query();
		query.where(cb().equal(query.from(User.class).get(User_.uuid), uuid));
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
