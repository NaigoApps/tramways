package tramways.inbound;

import java.util.List;

import tramways.core.model.persistable.projects.Project;
import tramways.inbound.model.CreateProjectRequest;

public interface ProjectService {

	Project createProject(CreateProjectRequest request);
	List<Project> retrieveUserProjects(String userUuid);
	Project retrieveProject(String uuid);
	void updateProject(String uuid, String name);
	void deleteProject(String uuid);
	void duplicateMap(String projectUuid, String mapUuid, String mapName);
	void editMap(String projectUuid, String mapUuid, String map);
	void deleteMap(String projectUuid, String mapUuid);

}
