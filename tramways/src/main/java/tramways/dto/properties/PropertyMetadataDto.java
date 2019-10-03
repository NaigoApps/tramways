package tramways.dto.properties;

import tramways.dto.Dto;

public class PropertyMetadataDto extends Dto {

	private String name;
	private String type;

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
