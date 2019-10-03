package tramways.model.properties;

import javax.persistence.Embeddable;
import javax.persistence.Lob;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import tramways.dto.properties.DecimalPropertyWrapper;
import tramways.dto.properties.DistributionPropertyWrapper;
import tramways.dto.properties.IntegerPropertyWrapper;
import tramways.dto.properties.PropertyWrapper;
import tramways.mapper.PropertyAdapterFactory;
import tramways.model.distributions.Distribution;

@Embeddable
public class Property {

	private static final Gson CONVERTER = new GsonBuilder()
			.registerTypeAdapterFactory(PropertyAdapterFactory.getFactory())
			.create();
	
	@Lob
	private String content;

	String getContent() {
		return content;
	}

	void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return CONVERTER.fromJson(content, PropertyWrapper.class).getName();
	}

	public PropertyType getType() {
		return CONVERTER.fromJson(content, PropertyWrapper.class).getType();
	}

	public Object getValue() {
		return CONVERTER.fromJson(content, PropertyWrapper.class).getValue();
	}

	public void assignContent(PropertyWrapper wrapper) {
		setContent(CONVERTER.toJson(wrapper, PropertyWrapper.class));
	}
	
	public PropertyWrapper retrieveContent() {
		return CONVERTER.fromJson(content, PropertyWrapper.class);
	}
	
	public static Property create(String name, Long value) {
		IntegerPropertyWrapper wrapper = new IntegerPropertyWrapper();
		wrapper.setName(name);
		wrapper.setValue(value);
		Property prop = new Property();
		prop.assignContent(wrapper);
		return prop;
	}
	
	public static Property create(String name, Double value) {
		DecimalPropertyWrapper wrapper = new DecimalPropertyWrapper();
		wrapper.setName(name);
		wrapper.setValue(value);
		Property prop = new Property();
		prop.assignContent(wrapper);
		return prop;
	}
	
	public static Property create(String name, Distribution value) {
		DistributionPropertyWrapper wrapper = new DistributionPropertyWrapper();
		wrapper.setName(name);
		wrapper.setValue(value);
		Property prop = new Property();
		prop.assignContent(wrapper);
		return prop;
	}
}
