package tramways.model;

public interface Configuration<T> {
	public String getName();
	public void configure(T obj);
}
