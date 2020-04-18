package tramways.dto.mappers;

import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;
import tramways.core.model.analysis.AnalysisType;
import tramways.dto.AnalysisTypeDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-04-13T17:27:11+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11 (Oracle Corporation)"
)
@ApplicationScoped
public class AnalysisTypeMapperImpl implements AnalysisTypeMapper {

    @Override
    public AnalysisTypeDto map(AnalysisType at) {
        if ( at == null ) {
            return null;
        }

        AnalysisTypeDto analysisTypeDto = new AnalysisTypeDto();

        analysisTypeDto.setName( at.getName() );
        analysisTypeDto.setId( at.getId() );

        return analysisTypeDto;
    }
}
