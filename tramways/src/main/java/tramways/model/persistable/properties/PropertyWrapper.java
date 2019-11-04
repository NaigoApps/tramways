package tramways.model.persistable.properties;

import javax.persistence.Embeddable;
import javax.persistence.Lob;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import tramways.mapper.PropertyAdapterFactory;
import tramways.model.distributions.Distribution;
import tramways.model.properties.DecimalProperty;
import tramways.model.properties.DistributionProperty;
import tramways.model.properties.IntegerProperty;
import tramways.model.properties.Property;
import tramways.model.properties.PropertyType;

@Embeddable
public class PropertyWrapper {

	private static final Gson CONVERTER = new GsonBuilder()
			.registerTypeAdapterFactory(PropertyAdapterFactory.getFactory())
			.create();
	
	@Lob
	private String content;

	public String getName() {
		return CONVERTER.fromJson(content, Property.class).getName();
	}

	public PropertyType getType() {
		return CONVERTER.fromJson(content, Property.class).getType();
	}

	public Object getValue() {
		return CONVERTER.fromJson(content, Property.class).getValue();
	}

	public void assignContent(Property p) {
		this.content = CONVERTER.toJson(p, Property.class);
	}
	
	public Property retrieveContent() {
		return CONVERTER.fromJson(content, Property.class);
	}
	
	public static PropertyWrapper create(String name, Long value) {
		IntegerProperty wrapper = new IntegerProperty();
		wrapper.setName(name);
		wrapper.setValue(value);
		PropertyWrapper prop = new PropertyWrapper();
		prop.assignContent(wrapper);
		return prop;
	}
	
	public static PropertyWrapper create(String name, Double value) {
		DecimalProperty wrapper = new DecimalProperty();
		wrapper.setName(name);
		wrapper.setValue(value);
		PropertyWrapper prop = new PropertyWrapper();
		prop.assignContent(wrapper);
		return prop;
	}
	
	public static PropertyWrapper create(String name, Distribution value) {
		DistributionProperty wrapper = new DistributionProperty();
		wrapper.setName(name);
		wrapper.setValue(value);
		PropertyWrapper prop = new PropertyWrapper();
		prop.assignContent(wrapper);
		return prop;
	}
}
