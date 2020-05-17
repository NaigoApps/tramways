package tramways.dto.mappers;

import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;
import tramways.inbound.model.RoadMap;
import tramways.inbound.model.RoadMapDescription;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-09T10:05:44+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11 (Oracle Corporation)"
)
@ApplicationScoped
public class RoadMapMapperImpl implements RoadMapMapper {

    @Override
    public RoadMap map(tramways.core.model.persistable.projects.RoadMap m) {
        if ( m == null ) {
            return null;
        }

        RoadMap roadMap = new RoadMap();

        roadMap.setUuid( m.getUuid() );
        roadMap.setName( m.getName() );
        roadMap.setContent( m.getContent() );

        roadMap.setResourceType( "RoadMap" );

        return roadMap;
    }

    @Override
    public RoadMapDescription description(tramways.core.model.persistable.projects.RoadMap map) {
        if ( map == null ) {
            return null;
        }

        RoadMapDescription roadMapDescription = new RoadMapDescription();

        roadMapDescription.setName( map.getName() );

        return roadMapDescription;
    }
}
