package tramways.dto.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import tramways.core.model.persistable.configurations.Configuration;
import tramways.core.model.properties.Property;
import tramways.dto.ConfigurationDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-04-13T17:27:10+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11 (Oracle Corporation)"
)
@ApplicationScoped
public class ConfigurationMapperImpl implements ConfigurationMapper {

    @Inject
    private PropertyMapper propertyMapper;

    @Override
    public Configuration map(ConfigurationDto dto) {
        if ( dto == null ) {
            return null;
        }

        Configuration configuration = new Configuration();

        configuration.setUuid( dto.getUuid() );
        configuration.setProperties( propertyListToPropertyWrapperList( dto.getProperties() ) );
        configuration.setName( dto.getName() );

        return configuration;
    }

    @Override
    public ConfigurationDto map(Configuration dto) {
        if ( dto == null ) {
            return null;
        }

        ConfigurationDto configurationDto = new ConfigurationDto();

        configurationDto.setUuid( dto.getUuid() );
        configurationDto.setName( dto.getName() );
        configurationDto.setProperties( propertyWrapperListToPropertyList( dto.getProperties() ) );

        return configurationDto;
    }

    protected List<PropertyWrapper> propertyListToPropertyWrapperList(List<Property> list) {
        if ( list == null ) {
            return null;
        }

        List<PropertyWrapper> list1 = new ArrayList<PropertyWrapper>( list.size() );
        for ( Property property : list ) {
            list1.add( propertyMapper.map( property ) );
        }

        return list1;
    }

    protected List<Property> propertyWrapperListToPropertyList(List<PropertyWrapper> list) {
        if ( list == null ) {
            return null;
        }

        List<Property> list1 = new ArrayList<Property>( list.size() );
        for ( PropertyWrapper propertyWrapper : list ) {
            list1.add( propertyMapper.map( propertyWrapper ) );
        }

        return list1;
    }
}
