package tramways.mapper.distributions;

import org.mapstruct.Mapper;

import tramways.dto.distributions.ConstantDistributionDto;
import tramways.mapper.MapperConfiguration;
import tramways.model.distributions.ConstantDistribution;

@Mapper(config = MapperConfiguration.class)
public interface ConstantDistributionMapper {

	public ConstantDistributionDto map(ConstantDistribution value);
	public ConstantDistribution map(ConstantDistributionDto value);
	
}
