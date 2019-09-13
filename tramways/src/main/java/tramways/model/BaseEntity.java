package tramways.model;

import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String uuid;
	
	public BaseEntity() {
		uuid = UUID.randomUUID().toString();
	}

	public Long getId() {
		return id;
	}

	public String getUuid() {
		return uuid;
	}
}
