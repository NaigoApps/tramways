package tramways.mapper.distributions;

import javax.inject.Inject;

import org.mapstruct.Mapper;

import tramways.dto.distributions.ConstantDistributionDto;
import tramways.dto.distributions.DistributionDto;
import tramways.dto.distributions.DistributionDtoVisitor;
import tramways.dto.distributions.ExponentialDistributionDto;
import tramways.dto.distributions.UniformDistributionDto;
import tramways.mapper.MapperConfiguration;
import tramways.model.distributions.ConstantDistribution;
import tramways.model.distributions.Distribution;
import tramways.model.distributions.DistributionVisitor;
import tramways.model.distributions.ExponentialDistribution;
import tramways.model.distributions.UniformDistribution;

@Mapper(config = MapperConfiguration.class)
public abstract class DistributionMapper implements DistributionVisitor, DistributionDtoVisitor {

	@Inject
	private ConstantDistributionMapper constantMapper;

	@Inject
	private ExponentialDistributionMapper exponentialMapper;

	@Inject
	private UniformDistributionMapper uniformMapper;

	
	private DistributionDto dtoResult;
	private Distribution result;
	
	public DistributionDto map(Distribution value) {
		value.accept(this);
		return this.dtoResult;
	}

	public Distribution map(DistributionDto value) {
		value.accept(this);
		return this.result;
	}

	@Override
	public void visit(ConstantDistribution constantDistribution) {
		this.dtoResult = constantMapper.map(constantDistribution);
	}

	@Override
	public void visit(ConstantDistributionDto d) {
		this.result = constantMapper.map(d);
	}

	@Override
	public void visit(ExponentialDistribution exponentialDistribution) {
		this.dtoResult = exponentialMapper.map(exponentialDistribution);
	}

	@Override
	public void visit(ExponentialDistributionDto d) {
		this.result = exponentialMapper.map(d);
	}

	@Override
	public void visit(UniformDistribution uniformDistribution) {
		this.dtoResult = uniformMapper.map(uniformDistribution);
	}

	@Override
	public void visit(UniformDistributionDto d) {
		this.result = uniformMapper.map(d);
	}
}
