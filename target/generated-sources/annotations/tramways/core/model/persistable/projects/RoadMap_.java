package tramways.core.model.persistable.projects;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RoadMap.class)
public abstract class RoadMap_ extends tramways.core.model.persistable.BaseEntity_ {

	public static volatile SingularAttribute<RoadMap, String> name;
	public static volatile ListAttribute<RoadMap, Analysis> analysis;
	public static volatile SingularAttribute<RoadMap, String> map;

	public static final String NAME = "name";
	public static final String ANALYSIS = "analysis";
	public static final String MAP = "map";

}

