package tramways.core.model.persistable.projects;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Project.class)
public abstract class Project_ extends tramways.core.model.persistable.BaseEntity_ {

	public static volatile SingularAttribute<Project, String> ownerUuid;
	public static volatile SetAttribute<Project, RoadMap> maps;
	public static volatile SingularAttribute<Project, String> name;

	public static final String OWNER_UUID = "ownerUuid";
	public static final String MAPS = "maps";
	public static final String NAME = "name";

}

