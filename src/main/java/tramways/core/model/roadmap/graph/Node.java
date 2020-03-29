package tramways.core.model.roadmap.graph;

import java.util.List;

public interface Node {
	public List<Arc> getSources();
	public List<Arc> getDestinations();
}
