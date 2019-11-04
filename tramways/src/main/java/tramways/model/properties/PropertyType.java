package tramways.model.properties;

import tramways.model.distributions.Distribution;

public enum PropertyType {
	INTEGER, STRING, DECIMAL, DISTRIBUTION;

	public static Property wrap(Object value) {
		if (value instanceof Long) {
			IntegerProperty wrapper = new IntegerProperty();
			wrapper.setValue(Long.class.cast(value));
			return wrapper;
		}
		if (value instanceof Double) {
			DecimalProperty wrapper = new DecimalProperty();
			wrapper.setValue(Double.class.cast(value));
			return wrapper;
		}
		if (value instanceof String) {
			StringProperty wrapper = new StringProperty();
			wrapper.setValue(String.class.cast(value));
			return wrapper;
		}
		if (value instanceof Distribution) {
			DistributionProperty wrapper = new DistributionProperty();
			wrapper.setValue(Distribution.class.cast(value));
			return wrapper;
		}
		return null;
	}
}
