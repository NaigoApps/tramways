package tramways.dto.mappers;

import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;
import tramways.core.model.persistable.projects.Analysis;
import tramways.inbound.model.AnalysisDescription;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-21T17:17:50+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.8 (Ubuntu)"
)
@ApplicationScoped
public class AnalysisMapperImpl implements AnalysisMapper {

    @Override
    public tramways.inbound.model.Analysis map(Analysis a) {
        if ( a == null ) {
            return null;
        }

        tramways.inbound.model.Analysis analysis = new tramways.inbound.model.Analysis();

        analysis.setUuid( a.getUuid() );
        analysis.setName( a.getName() );
        analysis.setStatus( a.getStatus() );
        analysis.setResult( a.getResult() );

        analysis.setResourceType( "Analysis" );

        return analysis;
    }

    @Override
    public AnalysisDescription description(Analysis analysis) {
        if ( analysis == null ) {
            return null;
        }

        AnalysisDescription analysisDescription = new AnalysisDescription();

        analysisDescription.setUuid( analysis.getUuid() );
        analysisDescription.setName( analysis.getName() );
        analysisDescription.setStatus( analysis.getStatus() );

        analysisDescription.setResourceType( "AnalysisDescription" );

        return analysisDescription;
    }
}
