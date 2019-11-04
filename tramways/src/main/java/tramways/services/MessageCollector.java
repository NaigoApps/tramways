package tramways.services;

import java.util.List;

public interface MessageCollector {
	public boolean addMessage(String message);
	public List<String> listMessages();
}
