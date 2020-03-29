package tramways.outbound;

import tramways.outbound.jpa.JPA;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

public class ProjectRepositoryProducer {

    @Inject
    @JPA
    private ProjectRepository repository;

    @Produces
    public ProjectRepository getRepository(){
        return repository;
    }

}
