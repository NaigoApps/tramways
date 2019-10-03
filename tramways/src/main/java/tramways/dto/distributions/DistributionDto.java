package tramways.dto.distributions;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = ExponentialDistributionDto.class, name = "exponential"),
		@Type(value = ConstantDistributionDto.class, name = "constant"),
		@Type(value = UniformDistributionDto.class, name = "uniform") })
public interface DistributionDto {
	public void accept(DistributionDtoVisitor visitor);
}
