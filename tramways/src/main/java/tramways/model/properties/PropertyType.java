package tramways.model.properties;

import tramways.dto.properties.DecimalPropertyWrapper;
import tramways.dto.properties.DistributionPropertyWrapper;
import tramways.dto.properties.IntegerPropertyWrapper;
import tramways.dto.properties.PropertyWrapper;
import tramways.dto.properties.StringPropertyWrapper;
import tramways.model.distributions.Distribution;

public enum PropertyType {
	INTEGER, STRING, DECIMAL, DISTRIBUTION;

	public static PropertyWrapper wrap(Object value) {
		if (value instanceof Long) {
			IntegerPropertyWrapper wrapper = new IntegerPropertyWrapper();
			wrapper.setValue(Long.class.cast(value));
			return wrapper;
		}
		if (value instanceof Double) {
			DecimalPropertyWrapper wrapper = new DecimalPropertyWrapper();
			wrapper.setValue(Double.class.cast(value));
			return wrapper;
		}
		if (value instanceof String) {
			StringPropertyWrapper wrapper = new StringPropertyWrapper();
			wrapper.setValue(String.class.cast(value));
			return wrapper;
		}
		if (value instanceof Distribution) {
			DistributionPropertyWrapper wrapper = new DistributionPropertyWrapper();
			wrapper.setValue(Distribution.class.cast(value));
			return wrapper;
		}
		return null;
	}
}
