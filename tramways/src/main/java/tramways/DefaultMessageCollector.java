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
	public void addMessage(String message) {
		messages.add(message);
	}

	@Override
	public List<String> listMessages() {
		return new ArrayList<>(messages);
	}

}
