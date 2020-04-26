package tramways.dto.mappers;

import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;
import tramways.inbound.model.AnalysisType;
import tramways.inbound.model.RoadMapContent;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-04-26T14:37:29+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11 (Oracle Corporation)"
)
@ApplicationScoped
public class AnalysisTypeMapperImpl implements AnalysisTypeMapper {

    @Override
    public AnalysisType map(tramways.core.model.analysis.AnalysisType type, RoadMapContent roadMap) {
        if ( type == null && roadMap == null ) {
            return null;
        }

        AnalysisType analysisType = new AnalysisType();

        if ( type != null ) {
            analysisType.setName( type.getName() );
            analysisType.setId( type.getId() );
        }
        analysisType.setParameters( type.getParameters(roadMap) );

        return analysisType;
    }
}
