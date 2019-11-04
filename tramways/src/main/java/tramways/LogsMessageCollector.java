package tramways;

import java.util.Collections;
import java.util.List;

import org.slf4j.LoggerFactory;

import tramways.services.MessageCollector;

public class LogsMessageCollector implements MessageCollector{

	@Override
	public boolean addMessage(String message) {
		System.out.println(message);
		LoggerFactory.getLogger(getClass()).info(message);
		return false;
	}

	@Override
	public List<String> listMessages() {
		return Collections.emptyList();
	}

}
