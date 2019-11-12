package tramways.outbound;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.LoggerFactory;

import tramways.core.model.persistable.BaseEntity;
import tramways.model.persistable.BaseEntity_;

public abstract class AbstractJPARepository<E extends BaseEntity> {

	@PersistenceContext
	private EntityManager em;

	protected void persist(BaseEntity... entities) {
		Arrays.stream(entities).forEach(entity -> em.persist(entity));
	}

	protected EntityManager getEntityManager() {
		return em;
	}

	protected void setEntityManager(EntityManager em) {
		this.em = em;
	}

	protected E findByUuid(String uuid) {
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

	protected E findById(Long id) {
		try {
			CriteriaQuery<E> query = query();
			Root<E> root = query.from(getEntityClass());
			query.where(cb().equal(root.get(BaseEntity_.id), id));
			return getEntityManager().createQuery(query).getSingleResult();
		} catch (Exception ex) {
			LoggerFactory.getLogger(getClass()).error("Error", ex);
		}
		return null;
	}

	protected List<E> findAll() {
		try {
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<E> query = builder.createQuery(getEntityClass());
			query.from(getEntityClass());
			return getEntityManager().createQuery(query).getResultList();
		} catch (Exception ex) {
			LoggerFactory.getLogger(getClass()).error("Error", ex);
		}
		return Collections.emptyList();
	}

	protected void delete(BaseEntity entity) {
		em.remove(entity);
	}

	protected void deleteById(Long id) {
		em.remove(findById(id));
	}

	protected void deleteByUuid(String uuid) {
		em.remove(findByUuid(uuid));
	}
	
	protected CriteriaBuilder cb() {
		return em.getCriteriaBuilder();
	}
	
	protected CriteriaQuery<E> query(){
		return em.getCriteriaBuilder().createQuery(getEntityClass());
	}

    protected abstract Class<E> getEntityClass();
}
