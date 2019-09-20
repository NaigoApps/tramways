package tramways.mapper.distributions;

import org.mapstruct.Mapper;

import tramways.dto.distributions.ExponentialDistributionDto;
import tramways.mapper.MapperConfiguration;
import tramways.model.distributions.ExponentialDistribution;

@Mapper(config = MapperConfiguration.class)
public interface ExponentialDistributionMapper {

	public ExponentialDistributionDto map(ExponentialDistribution value);
	public ExponentialDistribution map(ExponentialDistributionDto value);
	
}
