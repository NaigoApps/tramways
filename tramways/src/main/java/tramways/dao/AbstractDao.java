package tramways.dao;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;

import tramways.model.BaseEntity;
import tramways.model.BaseEntity_;

public abstract class AbstractDao<E extends BaseEntity> implements Dao<E> {

	@PersistenceContext(name = "restaurant-pu")
	private EntityManager em;

	@Transactional
	public void persist(BaseEntity... entities) {
		Arrays.stream(entities).forEach(entity -> em.persist(entity));
	}

	public EntityManager getEntityManager() {
		return em;
	}

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Override
	public E findByUuid(String uuid) {
		try {
			CriteriaQuery<E> query = query();
			Root<E> root = query.from(getEntityClass());
			query.where(cb().equal(root.get(BaseEntity_.uuid), uuid));
			return getEntityManager().createQuery(query).getSingleResult();
		} catch (Exception ex) {
			LoggerFactory.getLogger(getClass()).error("Error", ex);
		}
		return null;
	}

	@Override
	public List<E> findAll() {
		try {
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<E> query = builder.createQuery(getEntityClass());
			return getEntityManager().createQuery(query).getResultList();
		} catch (Exception ex) {
			LoggerFactory.getLogger(getClass()).error("Error", ex);
		}
		return Collections.emptyList();
	}

	@Override
	public void delete(BaseEntity entity) {
		em.remove(entity);
	}

	protected CriteriaBuilder cb() {
		return em.getCriteriaBuilder();
	}
	
	protected CriteriaQuery<E> query(){
		return em.getCriteriaBuilder().createQuery(getEntityClass());
	}

}
