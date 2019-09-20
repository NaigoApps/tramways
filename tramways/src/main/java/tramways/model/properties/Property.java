package tramways.model.properties;

import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
public class Property {

	private String name;
	
	@Lob
	private String content;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	public <T> T getValue(Class<T> valueClass) {
		return valueClass.cast(prop.getValue());
	}

	public abstract void accept(PropertyVisitor visitor);
}
