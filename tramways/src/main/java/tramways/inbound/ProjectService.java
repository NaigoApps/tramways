package tramways.inbound;

import java.util.List;

import tramways.core.model.persistable.projects.Project;

public interface ProjectService {

	public void createProject(Project project);
	public List<Project> retrieveProjects(String userUuid);
	public Project retrieveProject(String uuid);
	public void updateProject(String uuid, String name);
	public void deleteProject(String uuid);
	public void duplicateMap(String projectUuid, String mapUuid, String mapName);
	public void editMap(String projectUuid, String mapUuid, String map);
	public void deleteMap(String projectUuid, String mapUuid);


}
