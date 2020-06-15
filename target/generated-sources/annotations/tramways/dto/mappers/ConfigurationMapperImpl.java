package tramways.dto.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;
import tramways.core.model.persistable.configurations.Configuration;
import tramways.inbound.model.ItemConfiguration;
import tramways.inbound.model.Property;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-08T22:53:39+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11 (Oracle Corporation)"
)
@ApplicationScoped
public class ConfigurationMapperImpl implements ConfigurationMapper {

    @Override
    public ItemConfiguration map(Configuration configuration) {
        if ( configuration == null ) {
            return null;
        }

        ItemConfiguration itemConfiguration = new ItemConfiguration();

        List<Property> list = configuration.getProperties();
        if ( list != null ) {
            itemConfiguration.setProps( new ArrayList<Property>( list ) );
        }
        itemConfiguration.setUuid( configuration.getUuid() );
        itemConfiguration.setCategory( configuration.getCategory() );
        itemConfiguration.setName( configuration.getName() );

        itemConfiguration.setResourceType( "ItemConfiguration" );

        return itemConfiguration;
    }
}
