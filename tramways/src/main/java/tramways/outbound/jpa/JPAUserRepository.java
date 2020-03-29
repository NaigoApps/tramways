package tramways.outbound.jpa;

import org.slf4j.LoggerFactory;
import tramways.core.model.persistable.BaseEntity_;
import tramways.core.model.persistable.users.Role;
import tramways.core.model.persistable.users.User;
import tramways.core.model.persistable.users.User_;
import tramways.outbound.UserRepository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

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
            query.select(query.from(User.class));
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
        query.where(cb().equal(root.get(User_.username), username));
        try {
            return getEntityManager().createQuery(query).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public User findByUuid(String uuid) {
        CriteriaQuery<User> query = query(root -> cb().equal(root.get(BaseEntity_.uuid), uuid));
        try {
            return getEntityManager().createQuery(query).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

	private CriteriaQuery<User> query(Function<Root<User>, Predicate> predicate) {
    	return query(Collections.singletonList(predicate));
	}

	private CriteriaQuery<User> query(List<Function<Root<User>, Predicate>> predicates) {
        CriteriaQuery<User> query = query();
        Root<User> root = query.from(User.class);
        query.select(root);
        query.where(predicates.stream()
                .map(predicate -> predicate.apply(root))
                .toArray(Predicate[]::new));
        return query;
    }

    @Override
    public void deleteByUuid(String uuid) {
        getEntityManager().remove(findByUuid(uuid));
    }

    @Override
    public Long countByRole(Role role) {
        CriteriaQuery<Long> query = query(Long.class);
        query.select(cb().count(query.from(User.class)));
        query.where(cb().isMember(role, query.from(User.class).get(User_.ROLES)));
        return getEntityManager().createQuery(query).getSingleResult();
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

}
