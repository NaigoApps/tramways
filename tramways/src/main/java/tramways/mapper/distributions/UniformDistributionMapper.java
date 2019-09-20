package tramways.mapper.distributions;

import org.mapstruct.Mapper;

import tramways.dto.distributions.UniformDistributionDto;
import tramways.mapper.MapperConfiguration;
import tramways.model.distributions.UniformDistribution;

@Mapper(config = MapperConfiguration.class)
public interface UniformDistributionMapper {

	public UniformDistributionDto map(UniformDistribution value);
	public UniformDistribution map(UniformDistributionDto value);
	
}
