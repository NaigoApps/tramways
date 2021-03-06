package tramways.outbound.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import tramways.core.model.persistable.projects.Project;
import tramways.outbound.ProjectRepository;

@Mongo
public class ProjectsMongoRepository implements ProjectRepository {

    private static final String DATABASE = "tramways";
    private static final String COLLECTION = "projects";

    @Inject
    private MongoClient mongoClient;

    @Override
    public Project create(Project p) {
        getCollection().insertOne(p);
        return p;
    }

    @Override
    public Project findById(String id) {
        return getCollection().find().first();
    }

    @Override
    public List<Project> findByUser(String userUuid) {
        List<Project> result = new ArrayList<>();
        Iterator<Project> iterator = getCollection().find().iterator();
        while(iterator.hasNext()){
            result.add(iterator.next());
        }
        return result;
    }

    @Override
    public Project update(String id, String name) {
        return findById(id);
    }

    @Override
    public void delete(String id) {
    }

    @Override
    public void deleteMap(String mapId) {

    }

    @Override
    public void deleteByUser(String id) {

    }

    @Override
    public void deleteAnalysis(String analysisId) {

    }

    private MongoCollection<Project> getCollection() {
        return mongoClient.getDatabase(DATABASE).getCollection(COLLECTION, Project.class);
    }
}
