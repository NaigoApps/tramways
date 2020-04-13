package tramways.dto.mappers;

import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;
import tramways.core.model.persistable.projects.RoadMap;
import tramways.inbound.model.RoadMapDescription;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-04-13T14:51:40+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11 (Oracle Corporation)"
)
@ApplicationScoped
public class RoadMapMapperImpl implements RoadMapMapper {

    @Override
    public RoadMapDescription description(RoadMap map) {
        if ( map == null ) {
            return null;
        }

        RoadMapDescription roadMapDescription = new RoadMapDescription();

        roadMapDescription.setName( map.getName() );

        return roadMapDescription;
    }
}
