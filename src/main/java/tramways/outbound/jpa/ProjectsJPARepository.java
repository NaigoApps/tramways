package tramways.outbound.jpa;

import tramways.core.model.persistable.BaseEntity_;
import tramways.core.model.persistable.projects.Project;
import tramways.core.model.persistable.projects.Project_;
import tramways.core.model.persistable.projects.RoadMap;
import tramways.outbound.AbstractJPARepository;
import tramways.outbound.ProjectRepository;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@JPA
public class ProjectsJPARepository extends AbstractJPARepository<Project> implements ProjectRepository {

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
        return findByUserImpl(userUuid);
    }

    private List<Project> findByUserImpl(String userUuid) {
        CriteriaQuery<Project> query = query();
        query.where(cb().equal(query.from(Project.class).get(Project_.owner).get(BaseEntity_.uuid), userUuid));
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
    public void deleteMap(String mapId) {
        RoadMap map = findByUuid(mapId, RoadMap.class);
        getEntityManager().remove(map);
    }

    @Override
    public void deleteByUser(String userId) {
        List<Project> userProjects = findByUserImpl(userId);
        userProjects.forEach(getEntityManager()::remove);
    }

    @Override
    protected Class<Project> getEntityClass() {
        return Project.class;
    }

}
