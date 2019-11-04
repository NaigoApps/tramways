package tramways;

import java.util.ArrayList;
import java.util.List;

import tramways.services.MessageCollector;

public class DefaultMessageCollector implements MessageCollector{

	private List<String> messages;
	
	public DefaultMessageCollector() {
		messages = new ArrayList<>();
	}
	
	@Override
	public boolean addMessage(String message) {
		messages.add(message);
		return false;
	}

	@Override
	public List<String> listMessages() {
		return new ArrayList<>(messages);
	}

}
