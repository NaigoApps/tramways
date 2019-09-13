package tramways.model;

import java.util.List;

public abstract class AbstractConfiguration<T> implements Configuration<T> {

	private String name;
	
	public AbstractConfiguration(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public void configure(List<T> objs) {
		objs.forEach(this::configure);
	}
}
