package tramways.mapper.properties;

import javax.inject.Inject;

import org.mapstruct.Mapper;

import tramways.dto.properties.DecimalPropertyDto;
import tramways.dto.properties.DistributionPropertyDto;
import tramways.dto.properties.IntegerPropertyDto;
import tramways.dto.properties.PropertyDto;
import tramways.dto.properties.PropertyDtoVisitor;
import tramways.dto.properties.StringPropertyDto;
import tramways.mapper.MapperConfiguration;
import tramways.model.properties.DecimalProperty;
import tramways.model.properties.DistributionProperty;
import tramways.model.properties.IntegerProperty;
import tramways.model.properties.Property;
import tramways.model.properties.PropertyVisitor;
import tramways.model.properties.StringProperty;

@Mapper(config = MapperConfiguration.class)
public abstract class PropertyMapper implements PropertyDtoVisitor, PropertyVisitor {

	@Inject
	private IntegerPropertyMapper integerMapper;

	@Inject
	private DecimalPropertyMapper decimalMapper;

	@Inject
	private StringPropertyMapper stringMapper;

	@Inject
	private DistributionPropertyMapper distributionMapper;

	private Property result;
	private PropertyDto dtoResult;

	public PropertyDto map(Property model) {
		model.accept(this);
		return this.dtoResult;
	}

	public Property map(PropertyDto dto) {
		dto.accept(this);
		return this.result;
	}

	@Override
	public void visit(IntegerProperty d) {
		dtoResult = integerMapper.map(d);
	}

	@Override
	public void visit(DecimalProperty d) {
		dtoResult = decimalMapper.map(d);
	}

	@Override
	public void visit(StringProperty d) {
		dtoResult = stringMapper.map(d);
	}

	@Override
	public void visit(DistributionProperty d) {
		dtoResult = distributionMapper.map(d);
	}

	@Override
	public void visit(IntegerPropertyDto d) {
		result = integerMapper.map(d);
	}

	@Override
	public void visit(DecimalPropertyDto d) {
		result = decimalMapper.map(d);
	}

	@Override
	public void visit(StringPropertyDto d) {
		result = stringMapper.map(d);
	}

	@Override
	public void visit(DistributionPropertyDto d) {
		result = distributionMapper.map(d);
	}

}
