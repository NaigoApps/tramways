package tramways.model;

public interface Configurable {

	public void apply(Configuration conf);
	public Object getProperty(String name);

}
