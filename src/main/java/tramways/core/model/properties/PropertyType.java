package tramways.core.model.properties;

public enum PropertyType {
	INTEGER("integer"), STRING("string"), DECIMAL("decimal"), CHOICE("choice"), DISTRIBUTION("distribution");

	private String name;

	PropertyType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
