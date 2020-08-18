package tramways.outbound;

import java.util.List;
import tramways.core.model.persistable.projects.Project;

public interface ProjectRepository {

  Project create(Project p);

  Project findById(String id);

  List<Project> findByUser(String userUuid);

  Project update(String id, String name);

  void delete(String id);

  void deleteMap(String mapId);

  void deleteByUser(String id);

  void deleteAnalysis(String analysisId);
}
