package tramways.core.model.persistable.projects;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tramways.inbound.model.AnalysisStatus;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Analysis.class)
public abstract class Analysis_ extends tramways.core.model.persistable.BaseEntity_ {

	public static volatile SingularAttribute<Analysis, String> name;
	public static volatile SingularAttribute<Analysis, String> analysisResult;
	public static volatile SingularAttribute<Analysis, AnalysisStatus> status;

	public static final String NAME = "name";
	public static final String ANALYSIS_RESULT = "analysisResult";
	public static final String STATUS = "status";

}

