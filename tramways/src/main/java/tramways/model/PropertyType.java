package tramways.model;

import tramways.dto.distributions.DistributionDto;

public class PropertyType<T> {
	
	public static final PropertyType<Long> INTEGER_TYPE = new PropertyType<>(Long.class);
	public static final PropertyType<Double> DECIMAL_TYPE = new PropertyType<>(Double.class);
	public static final PropertyType<String> STRING_TYPE = new PropertyType<>(String.class);
	public static final PropertyType<DistributionDto> DISTRIBUTION_TYPE = new PropertyType<>(DistributionDto.class);
	
	private Class<T> type;
	
	private PropertyType(Class<T> type) {
		this.type = type;
	}
	
	public Class<T> getType() {
		return type;
	}
}
