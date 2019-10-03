package tramways.outbound.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import tramways.dao.AbstractJPARepository;
import tramways.model.auth.User_;
import tramways.model.projects.Project;
import tramways.model.projects.Project_;
import tramways.outbound.ProjectRepository;

public class JPAProjectRepository extends AbstractJPARepository<Project> implements ProjectRepository {

	@Override
	public Project create(Project p) {
		persist(p);
		return p;
	}

	@Override
	public Project findByUuid(String id) {
		return super.findByUuid(id);
	}
	
	@Override
	public List<Project> findByUser(String userUuid) {
		CriteriaQuery<Project> query = query();
		query.where(cb().equal(query.from(Project.class).get(Project_.owner).get(User_.uuid), userUuid));
		return getEntityManager().createQuery(query).getResultList();
	}

	@Override
	public Project update(String uuid, String name) {
		Project target = findByUuid(uuid);
		target.setName(name);
		return target;
	}

	@Override
	public void delete(String uuid) {
		deleteByUuid(uuid);
	}
	
	@Override
	protected Class<Project> getEntityClass() {
		return Project.class;
	}

}
