package tramways.dto.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import tramways.core.model.persistable.projects.Project;
import tramways.core.model.persistable.projects.RoadMap;
import tramways.inbound.model.ProjectDescription;
import tramways.inbound.model.RoadMapDescription;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-02T15:31:17+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.7 (Ubuntu)"
)
@ApplicationScoped
public class ProjectMapperImpl implements ProjectMapper {

    @Inject
    private RoadMapMapper roadMapMapper;

    @Override
    public tramways.inbound.model.Project map(Project p) {
        if ( p == null ) {
            return null;
        }

        tramways.inbound.model.Project project = new tramways.inbound.model.Project();

        project.setRoadMaps( roadMapSetToRoadMapDescriptionList( p.getMaps() ) );
        project.setUuid( p.getUuid() );
        project.setName( p.getName() );

        project.setResourceType( "Project" );

        return project;
    }

    @Override
    public List<tramways.inbound.model.Project> map(List<Project> projects) {
        if ( projects == null ) {
            return null;
        }

        List<tramways.inbound.model.Project> list = new ArrayList<tramways.inbound.model.Project>( projects.size() );
        for ( Project project : projects ) {
            list.add( map( project ) );
        }

        return list;
    }

    @Override
    public List<ProjectDescription> description(List<Project> projects) {
        if ( projects == null ) {
            return null;
        }

        List<ProjectDescription> list = new ArrayList<ProjectDescription>( projects.size() );
        for ( Project project : projects ) {
            list.add( projectToProjectDescription( project ) );
        }

        return list;
    }

    protected List<RoadMapDescription> roadMapSetToRoadMapDescriptionList(Set<RoadMap> set) {
        if ( set == null ) {
            return null;
        }

        List<RoadMapDescription> list = new ArrayList<RoadMapDescription>( set.size() );
        for ( RoadMap roadMap : set ) {
            list.add( roadMapMapper.description( roadMap ) );
        }

        return list;
    }

    protected ProjectDescription projectToProjectDescription(Project project) {
        if ( project == null ) {
            return null;
        }

        ProjectDescription projectDescription = new ProjectDescription();

        projectDescription.setUuid( project.getUuid() );
        projectDescription.setName( project.getName() );

        return projectDescription;
    }
}
