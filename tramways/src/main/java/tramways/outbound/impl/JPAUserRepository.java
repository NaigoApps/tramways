package tramways.outbound.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.LoggerFactory;

import tramways.model.persistable.BaseEntity_;
import tramways.model.persistable.users.User;
import tramways.model.persistable.users.User_;
import tramways.outbound.AbstractJPARepository;
import tramways.outbound.UserRepository;

public class JPAUserRepository extends AbstractJPARepository<User> implements UserRepository {

	@Override
	public User create(User u) {
		persist(u);
		return u;
	}

	@Override
	public List<User> findAll() {
		try {
			CriteriaQuery<User> query = query();
			Root<User> root = query.from(User.class);
			query.select(root);
			query.where(cb().notEqual(root.get(User_.toBeDeleted), true));
			return getEntityManager().createQuery(query).getResultList();
		} catch (Exception ex) {
			LoggerFactory.getLogger(getClass()).error("Error", ex);
		}
		return Collections.emptyList();
	}

	@Override
	public User findByUsername(String username) {
		CriteriaQuery<User> query = query();
		Root<User> root = query.from(User.class);
		query.select(root);
		query.where(cb().and(cb().equal(root.get(User_.username), username),
				cb().notEqual(root.get(User_.toBeDeleted), true)));
		try {
			return getEntityManager().createQuery(query).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

	@Override
	public User findByUuid(String uuid) {
		CriteriaQuery<User> query = query();
		Root<User> root = query.from(User.class);
		query.select(root);
		query.where(cb().and(cb().equal(root.get(BaseEntity_.uuid), uuid),
				cb().notEqual(root.get(User_.toBeDeleted), true)));
		try {
			return getEntityManager().createQuery(query).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

	@Override
	public void deleteByUuid(String uuid) {
		User target = findByUuid(uuid);
		target.setToBeDeleted(true);
	}

	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}

}
