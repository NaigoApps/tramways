package tramways.model.properties;

import tramways.dto.Dto;

public class PropertyMetadataDto extends Dto {

	private String name;
	private String type;
	
	public PropertyMetadataDto() {
		//Nothing to do
	}

	public PropertyMetadataDto(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
