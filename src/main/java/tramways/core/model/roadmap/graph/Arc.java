package tramways.core.model.roadmap.graph;

public interface Arc<T extends Node> {
	T getSource();
	T getDestination();
}
