package tramways.mapper.properties;

import org.mapstruct.Mapper;

import tramways.dto.properties.DistributionPropertyDto;
import tramways.mapper.MapperConfiguration;
import tramways.mapper.distributions.DistributionMapper;
import tramways.model.properties.DistributionProperty;

@Mapper(config = MapperConfiguration.class, uses = { DistributionMapper.class })
public interface DistributionPropertyMapper {

	public abstract DistributionPropertyDto map(DistributionProperty prop);

	public abstract DistributionProperty map(DistributionPropertyDto prop);

}
