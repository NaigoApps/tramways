package tramways.model;

import java.util.UUID;

public class Identifiable {
    private String uuid;

    public Identifiable() {
    	this.uuid = UUID.randomUUID().toString();
    }
    
    public void setUuid(String uuid) {
		this.uuid = uuid;
	}

    public String getUuid() {
        return uuid;
    }
}
