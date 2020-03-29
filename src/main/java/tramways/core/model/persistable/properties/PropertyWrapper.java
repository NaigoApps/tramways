package tramways.core.model.persistable.properties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tramways.core.model.distributions.Distribution;
import tramways.core.model.properties.*;

import javax.persistence.Embeddable;
import javax.persistence.Lob;
import java.io.IOException;

@Embeddable
public class PropertyWrapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(PropertyWrapper.class);

	private static final ObjectMapper CONVERTER = new ObjectMapper();
	public static final String ERROR = "Error";

	@Lob
	private String content;

	public String getName() {
		try {
			return CONVERTER.readValue(content, Property.class).getName();
		} catch (IOException e) {
			LOGGER.error(ERROR, e);
			return null;
		}
	}

	public PropertyType getType() {
		try {
			return CONVERTER.readValue(content, Property.class).getType();
		} catch (IOException e) {
			LOGGER.error(ERROR, e);
			return null;
		}
	}

	public Object getValue() {
		try {
			return CONVERTER.readValue(content, Property.class).getValue();
		} catch (IOException e) {
			LoggerFactory.getLogger(getClass()).error(ERROR, e);
			return null;
		}
	}

	public void assignContent(Property p) {
		try {
			this.content = CONVERTER.writeValueAsString(p);
		} catch (JsonProcessingException e) {
			LoggerFactory.getLogger(getClass()).error(ERROR, e);
		}
	}

	public Property retrieveContent() {
		try {
			return CONVERTER.readValue(content, Property.class);
		} catch (IOException e) {
			LoggerFactory.getLogger(getClass()).error(ERROR, e);
			return null;
		}
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
