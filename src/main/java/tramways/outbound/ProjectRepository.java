package tramways.outbound;

import tramways.core.model.persistable.projects.Project;

import java.util.List;

public interface ProjectRepository {
	Project create(Project p);
	Project findByUuid(String id);
	List<Project> findByUser(String userUuid);
	Project update(String id, String name);
	void delete(String id);
	void deleteMap(String mapId);
    void deleteByUser(String id);
}
