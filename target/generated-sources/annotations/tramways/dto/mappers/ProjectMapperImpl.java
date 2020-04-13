package tramways.dto.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import tramways.core.model.persistable.projects.Project;
import tramways.inbound.model.ProjectDescription;
import tramways.inbound.model.RoadMap;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-04-13T14:51:40+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11 (Oracle Corporation)"
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

        project.setRoadMaps( roadMapSetToRoadMapList( p.getMaps() ) );
        project.setUuid( p.getUuid() );
        project.setName( p.getName() );

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

    protected List<RoadMap> roadMapSetToRoadMapList(Set<tramways.core.model.persistable.projects.RoadMap> set) {
        if ( set == null ) {
            return null;
        }

        List<RoadMap> list = new ArrayList<RoadMap>( set.size() );
        for ( tramways.core.model.persistable.projects.RoadMap roadMap : set ) {
            list.add( roadMapMapper.map( roadMap ) );
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
