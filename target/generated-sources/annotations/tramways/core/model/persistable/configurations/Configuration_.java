package tramways.core.model.persistable.configurations;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Configuration.class)
public abstract class Configuration_ extends tramways.core.model.persistable.BaseEntity_ {

	public static volatile SingularAttribute<Configuration, String> name;
	public static volatile ListAttribute<Configuration, PropertyWrapper> properties;

	public static final String NAME = "name";
	public static final String PROPERTIES = "properties";

}

