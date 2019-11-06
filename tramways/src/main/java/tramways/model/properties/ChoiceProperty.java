package tramways.model.properties;

import java.util.Arrays;
import java.util.List;

public class ChoiceProperty extends Property {

	private List<String> options;
	private String value;

	public ChoiceProperty(String name, String... options) {
		super(name, PropertyType.CHOICE);
		this.options = Arrays.asList(options);
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}
	
	public void setOptions(List<String> options) {
		this.options = options;
	}
	
	public List<String> getOptions() {
		return options;
	}

}
