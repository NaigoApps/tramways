package tramways.services;

import java.util.List;

public interface MessageCollector {
	public void addMessage(String message);
	public List<String> listMessages();
}
