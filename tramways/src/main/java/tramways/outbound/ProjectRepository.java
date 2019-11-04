package tramways.outbound;

import java.util.List;

import tramways.model.persistable.projects.Project;

public interface ProjectRepository {
	public Project create(Project p);
	public Project findByUuid(String id);
	public List<Project> findByUser(String userUuid);
	public Project update(String id, String name);
	public void delete(String id);
}
