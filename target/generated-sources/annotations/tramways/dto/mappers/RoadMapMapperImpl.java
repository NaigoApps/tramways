package tramways.dto.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import tramways.core.model.persistable.projects.Analysis;
import tramways.inbound.model.AnalysisDescription;
import tramways.inbound.model.RoadMap;
import tramways.inbound.model.RoadMapDescription;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-15T23:52:10+0100",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@ApplicationScoped
public class RoadMapMapperImpl implements RoadMapMapper {

    @Inject
    private AnalysisMapper analysisMapper;

    @Override
    public RoadMap map(tramways.core.model.persistable.projects.RoadMap m) {
        if ( m == null ) {
            return null;
        }

        RoadMap roadMap = new RoadMap();

        roadMap.setUuid( m.getUuid() );
        roadMap.setName( m.getName() );
        roadMap.setContent( m.getContent() );
        roadMap.setAnalysis( analysisListToAnalysisDescriptionList( m.getAnalysis() ) );

        roadMap.setResourceType( "RoadMap" );

        return roadMap;
    }

    @Override
    public RoadMapDescription description(tramways.core.model.persistable.projects.RoadMap map) {
        if ( map == null ) {
            return null;
        }

        RoadMapDescription roadMapDescription = new RoadMapDescription();

        roadMapDescription.setUuid( map.getUuid() );
        roadMapDescription.setName( map.getName() );

        roadMapDescription.setResourceType( "RoadMapDescription" );

        return roadMapDescription;
    }

    protected List<AnalysisDescription> analysisListToAnalysisDescriptionList(List<Analysis> list) {
        if ( list == null ) {
            return null;
        }

        List<AnalysisDescription> list1 = new ArrayList<AnalysisDescription>( list.size() );
        for ( Analysis analysis : list ) {
            list1.add( analysisMapper.description( analysis ) );
        }

        return list1;
    }
}
