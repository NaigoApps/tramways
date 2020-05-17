package tramways.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tramways.core.model.persistable.configurations.Configuration;
import tramways.inbound.model.ItemConfiguration;

@Mapper(config = MapperConfiguration.class, uses = { })
public interface ConfigurationMapper {

    @Mapping(target = "props", source = "properties")
    @Mapping(target = "resourceType", constant = "ItemConfiguration")
    ItemConfiguration map(Configuration configuration);

}
