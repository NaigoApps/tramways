package tramways.dto.mappers;

import java.util.List;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;
import tramways.inbound.model.AnalysisType;
import tramways.inbound.model.Property;
import tramways.inbound.model.RoadMapContent;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-15T23:52:09+0100",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@ApplicationScoped
public class AnalysisTypeMapperImpl implements AnalysisTypeMapper {

    @Override
    public AnalysisType map(tramways.core.model.analysis.AnalysisType type, RoadMapContent roadMap, List<Property> properties) {
        if ( type == null && roadMap == null && properties == null ) {
            return null;
        }

        AnalysisType analysisType = new AnalysisType();

        if ( type != null ) {
            analysisType.setName( type.getName() );
            analysisType.setId( type.getId() );
        }

        return analysisType;
    }
}
