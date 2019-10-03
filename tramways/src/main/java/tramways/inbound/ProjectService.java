package tramways.inbound;

import java.util.List;

import tramways.model.projects.Project;

public interface ProjectService {

	public void createProject(Project project);
	public List<Project> retrieveProjects(String userUuid);
	public Project retrieveProject(String uuid);
	public void updateProject(String uuid, String name);
	public void deleteProject(String uuid);


}
