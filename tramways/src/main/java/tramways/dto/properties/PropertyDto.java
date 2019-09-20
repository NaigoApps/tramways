package tramways.dto.properties;

public abstract class PropertyDto {

	private String name;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract Object getValue();
	public abstract void accept(PropertyDtoVisitor visitor);
}
