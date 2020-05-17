package tramways.dto.mappers;

import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;
import tramways.core.model.persistable.projects.Analysis;
import tramways.dto.AnalysisDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-09T10:05:44+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11 (Oracle Corporation)"
)
@ApplicationScoped
public class AnalysisMapperImpl extends AnalysisMapper {

    @Override
    public AnalysisDto map(Analysis a) {
        if ( a == null ) {
            return null;
        }

        AnalysisDto analysisDto = new AnalysisDto();

        analysisDto.setUuid( a.getUuid() );
        analysisDto.setName( a.getName() );
        analysisDto.setAnalysisResult( a.getAnalysisResult() );

        return analysisDto;
    }

    @Override
    public Analysis map(AnalysisDto m) {
        if ( m == null ) {
            return null;
        }

        Analysis analysis = new Analysis();

        analysis.setUuid( m.getUuid() );
        analysis.setAnalysisResult( m.getAnalysisResult() );
        analysis.setName( m.getName() );

        return analysis;
    }
}
